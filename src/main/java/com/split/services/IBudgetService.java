package com.split.services;

import com.split.dto.RequestBudgetDto;
import com.split.dto.ResponseBudgetDto;


public interface IBudgetService {
    public ResponseBudgetDto addBudget (RequestBudgetDto requestBudgetDto);
    public ResponseBudgetDto updateBudget(RequestBudgetDto request);
}
