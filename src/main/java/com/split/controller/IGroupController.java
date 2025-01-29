package com.split.controller;

import com.split.entities.Group;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IGroupController {
    public ResponseEntity<Map<String, Object>> saveGroup(Group group);

    public ResponseEntity<Map<String, Object>> getUserGroups();

    public ResponseEntity<Map<String, Object>> retrieveGroupById(Integer id);

}
