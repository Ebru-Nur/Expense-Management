package com.split.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor//parametresiz constructor
@AllArgsConstructor//parametreli constructor
@Data
public class AddMemberRequest {
    private Integer userId;
    private Integer groupId;
}
