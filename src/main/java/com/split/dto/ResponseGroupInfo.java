package com.split.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

@AllArgsConstructor
public class ResponseGroupInfo {
    private Integer groupId;
    private String groupName;
    private Integer creatorId;
    private String creatorName;
    private List<MemberInfo> members;
}
