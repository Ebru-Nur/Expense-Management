package com.split.services.impl;

import com.split.dto.MemberInfo;
import com.split.dto.ResponseGroupDto;
import com.split.dto.ResponseGroupInfo;
import com.split.entities.Group;
import com.split.entities.Member;
import com.split.entities.User;
import com.split.repository.GroupRepository;
import com.split.repository.MemberRepository;
import com.split.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public ResponseGroupDto saveGroup(Group group) {
        try{
            group.setCreateDate();
            group.setActive(true);
            Group group1 =  groupRepository.save(group);
            return new ResponseGroupDto(group1.getId(), group1.getName(), group1.getCreator().getFirstName()+" "+group1.getCreator().getLastName(), group1.getCreateDate(), new ArrayList<>(), new ArrayList<>());
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public List<ResponseGroupDto> getUserGroups(Integer userId) {
        // 1) userId'ye ait tüm "member" kayıtlarını bul
        List<Member> memberships = memberRepository.findByUser_Id(userId);

        // 2) Her bir membership kaydından group bilgisine eriş
        List<Group> groupsList =  memberships.stream()
                .map(Member::getGroup)
                .collect(Collectors.toList());
        return groupsList.stream()
                .map(group -> new ResponseGroupDto(group.getId(), group.getName(), group.getCreator().getFirstName()+" "+group.getCreator().getLastName(), group.getCreateDate(), new ArrayList<>(), new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @Override
    public Group getGroupById(Integer id) {
        Optional<Group> optional = groupRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Group group = optional.get();
        return group;
    }

    @Override
    public ResponseGroupInfo retrieveGroupInfo(Integer groupId) {
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new RuntimeException("Grup bulunamadı (ID = " + groupId + ")"));

            List<MemberInfo> members = group.getMemberships().stream()
                    .map(member -> {
                        return new MemberInfo(
                                member.getUser().getId(),
                                member.getUser().getFirstName(),
                                member.getUser().getLastName(),
                                member.getUser().getEmail()
                        );
                    })
                    .collect(Collectors.toList());

            return new ResponseGroupInfo(
                    group.getId(),
                    group.getName(),
                    group.getCreator().getId(),
                    group.getCreator().getFirstName() + " " + group.getCreator().getLastName(),
                    members
            );
        }
    }


