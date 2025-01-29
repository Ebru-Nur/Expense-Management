package com.split.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfo {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
}
