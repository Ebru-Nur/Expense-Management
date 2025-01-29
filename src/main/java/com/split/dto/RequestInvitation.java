package com.split.dto;


import lombok.Data;

@Data
public class RequestInvitation{
    private Integer groupId;
    private String email;
    private Integer userId;
    private String invitationCode;
}
