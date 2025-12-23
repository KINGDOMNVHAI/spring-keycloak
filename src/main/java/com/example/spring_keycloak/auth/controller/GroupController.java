package com.example.spring_keycloak.auth.controller;

import com.example.spring_keycloak.auth.dto.request.GroupRequest;
import com.example.spring_keycloak.auth.entity.Group;
import com.example.spring_keycloak.auth.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/public/groups")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        Group savedGroup = groupService.saveGroup(request);
        return ResponseEntity.ok(savedGroup);
    }
}
