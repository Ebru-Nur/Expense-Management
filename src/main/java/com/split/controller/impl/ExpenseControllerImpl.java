package com.split.controller.impl;

import com.split.controller.IExpenseController;
import com.split.dto.DistributionDto;
import com.split.dto.RequestExpenseDto;
import com.split.dto.ResponseExpenseDto;
import com.split.entities.*;
import com.split.services.*;
import com.split.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expense")
public class ExpenseControllerImpl implements IExpenseController {

    @Autowired
    private IExpenseService expenseService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IDistributionService distributionService;

    @Autowired
    private IDeptService debtService;

     @Override
     @PostMapping(path = "/add")
     public ResponseEntity<Map<String, Object>> addExpense(@RequestBody RequestExpenseDto expense) {
         String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

         User user = userService.getUserById(Integer.parseInt(id));

         Group group = groupService.getGroupById(expense.getGroup());

         Category category = categoryService.getCategoryById(expense.getCategory_id());

         Expense newExpense = new Expense();
         newExpense.setTotalAmount(expense.getTotalAmount());
         newExpense.setGroup(group);
         newExpense.setOwner(user);
         newExpense.setPayingUser(user);
         newExpense.setCategory(category);
         newExpense.setCreateDate(LocalDateTime.now());

         Expense savedExpense = expenseService.addExpense(newExpense);

         double memberCount = group.getMemberships().size();
         double amountPerMember = expense.getTotalAmount() / memberCount;
         List<DistributionDto> distributionsDto = new ArrayList<>();

         group.getMemberships().forEach(member -> {
             User memberUser = member.getUser();

             if (!memberUser.equals(user)) {
                 Debt existingDebt = debtService.findDebtBetweenUsers(memberUser.getId(), user.getId());

                 if (existingDebt != null) {
                     existingDebt.setAmount(existingDebt.getAmount() + amountPerMember);
                     debtService.updateDebt(existingDebt);
                 } else {
                     Debt newDebt = new Debt();
                     newDebt.setObligor(memberUser);
                     newDebt.setLender(user);
                     newDebt.setAmount(amountPerMember);
                     debtService.addDebt(newDebt);
                 }
             }

             Distribution distribution = new Distribution();
             distribution.setExpense(savedExpense);
             distribution.setUser(memberUser);
             distribution.setAmount(amountPerMember);
             distributionService.addDistribution(distribution);

             distributionsDto.add(new DistributionDto(memberUser.getFirstName() + " " + memberUser.getLastName(), amountPerMember));
         });

         ResponseExpenseDto responseExpenseDto = new ResponseExpenseDto();
         responseExpenseDto.setAmount(savedExpense.getTotalAmount());
         responseExpenseDto.setCategoryName(savedExpense.getCategory().getName());
         responseExpenseDto.setCreateDate(savedExpense.getCreateDate());
         responseExpenseDto.setOwnerName(savedExpense.getOwner().getFirstName() + " " + savedExpense.getOwner().getLastName());
         responseExpenseDto.setDistributionList(distributionsDto);

         if (savedExpense == null) {
             return ResponseUtils.error("Expense cannot be built.");
         }
         return ResponseUtils.success("Expense successfully created.", responseExpenseDto);
     }


}
