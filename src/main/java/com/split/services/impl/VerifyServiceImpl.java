package com.split.services.impl;

import com.split.dto.RequestInvitation;
import com.split.entities.Group;
import com.split.entities.Verify;
import com.split.entities.Member;
import com.split.entities.User;
import com.split.repository.GroupRepository;
import com.split.repository.VerifyRepository;
import com.split.repository.MemberRepository;
import com.split.repository.UserRepository;
import com.split.services.IVerifyService;
import com.split.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerifyServiceImpl implements IVerifyService {

    @Autowired
    private VerifyRepository verifyRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MailService mailService;

    @Override
    public String createVerify(RequestInvitation request) {
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Grup bulunamadı (ID = " + request.getGroupId() + ")"));

        String invitationCode = UUID.randomUUID().toString();

        Verify verify = new Verify();
        verify.setGroup(group);
        verify.setEmail(request.getEmail());
        verify.setVerifycode(invitationCode);

        verifyRepository.save(verify);

        mailService.sendEmail(request.getEmail(), "Split Grup Davetiyesi", "Gruba katılm için " + invitationCode);
        return invitationCode;
    }

    @Override
    public boolean acceptVerify(String code, Integer userId) {
        Verify verify = verifyRepository.findByVerifycode(code);

        if (verify == null) {
            throw new RuntimeException("invalid kod");
        }


        if (verify.isAccepted()) {
            throw new RuntimeException("Already accepted Code");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı (ID = " + userId + ")"));

        Member member = new Member();
        member.setGroup(verify.getGroup());
        member.setUser(user);
        memberRepository.save(member);

        verify.setAccepted(true);
        verifyRepository.save(verify);
        return true;
    }
}
