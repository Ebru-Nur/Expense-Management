package com.split.controller.impl;

import com.split.controller.IGroupController;
import com.split.dto.AddMemberRequest;
import com.split.dto.ResponseGroupDto;
import com.split.dto.ResponseGroupInfo;
import com.split.entities.Group;
import com.split.entities.User;
import com.split.services.IGroupService;
import com.split.services.IMemberService;
import com.split.services.IUserService;
import com.split.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/group")
public class GroupControllerImpl implements IGroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMemberService memberService;

    @PostMapping(path = "/save")
    @Override
    public ResponseEntity<Map<String, Object>> saveGroup(@RequestBody Group group) {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getUserById(Integer.parseInt(id));
        group.setCreator(user);
        ResponseGroupDto savedGroup = groupService.saveGroup(group);
        if (savedGroup == null)
            return ResponseUtils.error("Group can not build.");
        memberService.addMember(new AddMemberRequest(user.getId(), savedGroup.getId()));
        return ResponseUtils.success("Group successfully created.", savedGroup);
    }

    @GetMapping(path = "/")
    @Override
    public ResponseEntity<Map<String, Object>> getUserGroups() {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<ResponseGroupDto> group = groupService.getUserGroups(Integer.parseInt(id));
        return ResponseUtils.success("", group);
    }



    @GetMapping(path = "/detail/{id}")
    @Override
    public ResponseEntity<Map<String, Object>> retrieveGroupById(@PathVariable Integer id) {
        ResponseGroupInfo response = groupService.retrieveGroupInfo(id);
        return ResponseUtils.success("Group Info", response);
    }
}



