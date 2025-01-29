package com.split.controller.impl;

import com.split.controller.IVerifyController;
import com.split.dto.RequestInvitation;
import com.split.services.IVerifyService;
import com.split.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/verify")
public class VerifyControllerImpl implements IVerifyController {

    @Autowired
    private IVerifyService verifyService;

    @PostMapping(path = "/")
    @Override
    public ResponseEntity<Map<String, Object>> saveInvitation(@RequestBody RequestInvitation req) {
        try{
            String code= verifyService.createVerify(req);
            return ResponseUtils.success("Invitation code created", code);
        } catch (Exception e) {
            return ResponseUtils.error(e.getMessage());
        }
    }
    @PostMapping(path = "/accept")
    @Override
    public ResponseEntity<Map<String, Object>> accept(@RequestBody RequestInvitation req) {
        try{
            String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            boolean result= verifyService.acceptVerify(req.getInvitationCode(), Integer.parseInt(id));
            if (result) {
                return ResponseUtils.success("Invitation accepted", null);
            } else {
                return ResponseUtils.error("Invitation not accepted");
            }
        } catch (Exception e) {
            return ResponseUtils.error(e.getMessage());
        }
    }
}
