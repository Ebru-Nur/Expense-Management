package com.split.controller;

import com.split.dto.RequestBudgetDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IBudgetController {

    public ResponseEntity<Map<String, Object>> saveBudget(RequestBudgetDto budget);
    public ResponseEntity<Map<String, Object>> updateBudget(RequestBudgetDto budget);
}
