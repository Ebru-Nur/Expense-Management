package com.split.services.impl;

import com.split.dto.AddMemberRequest;
import com.split.entities.Group;
import com.split.entities.Member;
import com.split.entities.User;
import com.split.repository.GroupRepository;
import com.split.repository.MemberRepository;
import com.split.repository.UserRepository;
import com.split.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    public boolean addMember(AddMemberRequest member) {
        User user = userRepository.findById(member.getUserId())
                .orElseThrow(() -> new RuntimeException("User could not be found!"));

        Group group = groupRepository.findById(member.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group could not be found!"));

        boolean exists = memberRepository.existsByUser_IdAndGroup_Id(
                member.getUserId(),
                member.getGroupId()
        );
        if (exists) {
            throw new RuntimeException("Kullanıcı zaten bu grubun üyesi!");
        }
        Member savedMember = new Member();
        savedMember.setUser(user);
        savedMember.setGroup(group);


        Member newMember = memberRepository.save(savedMember);

        return true;
    }
}
