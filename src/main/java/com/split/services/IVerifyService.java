package com.split.services;

import com.split.dto.RequestInvitation;

public interface IVerifyService {
    public String createVerify(RequestInvitation request);
    public boolean acceptVerify(String code,  Integer userId);

}
