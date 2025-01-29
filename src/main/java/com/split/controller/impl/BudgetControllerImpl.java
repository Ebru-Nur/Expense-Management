package com.split.controller.impl;

import com.split.controller.IBudgetController;
import com.split.dto.RequestBudgetDto;
import com.split.dto.ResponseBudgetDto;
import com.split.services.IBudgetService;
import com.split.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/budget")
public class BudgetControllerImpl implements IBudgetController {
    @Autowired
    private IBudgetService budgetService;

    @PostMapping(path = "/")
    @Override
    public ResponseEntity<Map<String, Object>> saveBudget(@RequestBody  RequestBudgetDto budget) {
        try {
            String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            budget.setUserId(Integer.parseInt(id));
            ResponseBudgetDto ress = budgetService.addBudget(budget);
            return ResponseUtils.success("Budget successfully created.", ress);
        } catch (Exception e) {
            return ResponseUtils.error("Budget can not create.");
        }
    }

    @PutMapping(path = "/")
    @Override
    public ResponseEntity<Map<String, Object>> updateBudget(@RequestBody RequestBudgetDto budget) {
       try {
           String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
           budget.setUserId(Integer.parseInt(id));
           ResponseBudgetDto ress = budgetService.updateBudget(budget);
           return ResponseUtils.success("Budget successfully updated.", ress);
       } catch (Exception e) {
           return ResponseUtils.error("Budget can not update.");
       }
    }
}
