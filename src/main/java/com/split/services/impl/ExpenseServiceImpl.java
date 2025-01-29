package com.split.services.impl;

import com.split.dto.DistributionDto;
import com.split.dto.RequestExpenseDto;
import com.split.dto.ResponseExpenseDto;
import com.split.entities.Category;
import com.split.entities.Distribution;
import com.split.entities.Expense;
import com.split.entities.Group;
import com.split.repository.DistributionRepository;
import com.split.repository.ExpenseRepository;
import com.split.repository.GroupRepository;
import com.split.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DistributionRepository distributionRepository;

    public Expense addExpense (Expense expense) {


        Expense saved = expenseRepository.save(expense);

        return saved;
    }



}
