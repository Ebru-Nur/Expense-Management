package com.split.controller;

import com.split.dto.RequestExpenseDto;
import com.split.entities.Expense;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IExpenseController {
    public ResponseEntity<Map<String, Object>> addExpense(RequestExpenseDto expense);

}
