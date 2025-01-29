package com.split.services.impl;

import com.split.dto.RequestBudgetDto;
import com.split.dto.ResponseBudgetDto;
import com.split.entities.Budget;
import com.split.entities.Group;
import com.split.entities.User;
import com.split.repository.BudgetRepository;
import com.split.repository.GroupRepository;
import com.split.repository.UserRepository;
import com.split.services.IBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements IBudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public ResponseBudgetDto addBudget(RequestBudgetDto requestBudgetDto) {
        User user = userRepository.findById(requestBudgetDto.getUserId())
                .orElseThrow(() -> new RuntimeException("user not found (ID = " + requestBudgetDto.getUserId() + ")"));

        Group group = groupRepository.findById(requestBudgetDto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Grup not found (ID = " + requestBudgetDto.getGroupId() + ")"));

        Budget budget = new Budget();
        budget.setGroup(group);
        budget.setUser(user);
        budget.setAmount(requestBudgetDto.getAmount());

        Budget savedBudget = budgetRepository.save(budget);

        return new ResponseBudgetDto(
                savedBudget.getId(),
                savedBudget.getGroup().getId(),
                savedBudget.getAmount(),
                savedBudget.getUser().getId()
        );
    }

@Override
    public ResponseBudgetDto updateBudget(RequestBudgetDto request) {
        userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı (ID = " + request.getUserId() + ")"));

        groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Grup bulunamadı (ID = " + request.getGroupId() + ")"));

        Budget budget = budgetRepository.findByUser_IdAndGroup_Id(request.getUserId(), request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        budget.setAmount(budget.getAmount() + request.getAmount());

        Budget updatedBudget = budgetRepository.save(budget);

        return new ResponseBudgetDto(
                updatedBudget.getId(),
                updatedBudget.getGroup().getId(),
                updatedBudget.getAmount(),
                updatedBudget.getUser().getId()
        );
    }
}
