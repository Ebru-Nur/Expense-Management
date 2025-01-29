package com.split.services;

import com.split.dto.AddMemberRequest;
import com.split.entities.Member;

public interface IMemberService {

    public boolean addMember(AddMemberRequest member);
}
