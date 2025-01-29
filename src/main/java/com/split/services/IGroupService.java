package com.split.services;

import com.split.dto.ResponseGroupDto;
import com.split.dto.ResponseGroupInfo;
import com.split.entities.Category;
import com.split.entities.Group;
import com.split.entities.User;

import java.util.List;

public interface IGroupService {
    public ResponseGroupDto saveGroup(Group group);

    public List<ResponseGroupDto> getUserGroups(Integer userId);

    public Group getGroupById(Integer id);

    public ResponseGroupInfo retrieveGroupInfo(Integer groupId);


}
