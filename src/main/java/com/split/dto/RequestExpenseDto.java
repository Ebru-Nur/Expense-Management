package com.split.dto;

import com.split.entities.Category;
import com.split.entities.Group;
import com.split.entities.User;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestExpenseDto {

    private Double totalAmount;
    private Integer group;
    private Integer owner_id;
    private Integer category_id;
}