package com.split.controller;

import com.split.dto.RequestInvitation;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IVerifyController {
    public ResponseEntity<Map<String, Object>> saveInvitation(RequestInvitation req);
    public ResponseEntity<Map<String, Object>> accept(RequestInvitation req);
}
