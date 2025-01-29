package com.split.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBudgetDto{
    private Integer id;
    private Integer groupId;
    private Double amount;
    private Integer userId;
}
