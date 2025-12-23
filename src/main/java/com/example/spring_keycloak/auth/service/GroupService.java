package com.example.spring_keycloak.auth.service;

import com.example.spring_keycloak.auth.dto.request.GroupRequest;
import com.example.spring_keycloak.auth.entity.Group;
import com.example.spring_keycloak.auth.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private KeycloakAdminService keycloakAdminService;

    public Group saveGroup(GroupRequest request) {
        Group group = new Group();
        if (request.getId() != null) {
            group.setId(request.getId());
        }
        group.setName(request.getName());
        group.setKcRoles(request.getKcRoles());
        Group savedGroup = groupRepository.save(group);
        keycloakAdminService.syncGroupWithKeycloak(savedGroup);
        return savedGroup;
    }





//    public void syncGroupsWithKeycloak(List<Group> dbGroups) {
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl("http://localhost:8080/auth")
//                .realm("demo-realm")
//                .clientId("demo-client")
//                .username("admin")
//                .password("admin")
//                .build();
//
//        String realm = "demo-realm";
//        List<GroupRepresentation> kcGroups = keycloak.realm(realm).groups().groups();
//
//        Map<String, GroupRepresentation> kcGroupMap = kcGroups.stream()
//                .collect(Collectors.toMap(GroupRepresentation::getName, Function.identity()));
//
//        Set<String> dbGroupNames = dbGroups.stream()
//                .map(Group::getName)
//                .collect(Collectors.toSet());
//
//        // 1. Insert or update
//        for (Group dbGroup : dbGroups) {
//            GroupRepresentation existing = kcGroupMap.get(dbGroup.getName());
//            if (existing == null) {
//                // Create new group
//                GroupRepresentation newGroup = new GroupRepresentation();
//                newGroup.setName(dbGroup.getName());
//                keycloak.realm(realm).groups().add(newGroup);
//                existing = keycloak.realm(realm).groups().groups().stream()
//                        .filter(g -> g.getName().equals(dbGroup.getName()))
//                        .findFirst().orElse(null);
//            }
//
//            // Sync roles
//            List<String> roles = parseRoles(dbGroup.getKcRoles());
//            List<RoleRepresentation> allRoles = keycloak.realm(realm).roles().list();
//            List<RoleRepresentation> matchedRoles = allRoles.stream()
//                    .filter(r -> roles.contains(r.getName()))
//                    .collect(Collectors.toList());
//
//            keycloak.realm(realm).groups().group(existing.getId()).roles().realmLevel().add(matchedRoles);
//        }
//
//        // 2. Delete groups not in DB
//        for (GroupRepresentation kcGroup : kcGroups) {
//            if (!dbGroupNames.contains(kcGroup.getName())) {
//                keycloak.realm(realm).groups().group(kcGroup.getId()).remove();
//            }
//        }
//    }
//
//    private List<String> parseRoles(String kcRoles) {
//        if (kcRoles == null || kcRoles.isEmpty()) return Collections.emptyList();
//        return Arrays.stream(kcRoles.replace("[", "").replace("]", "").split(","))
//                .map(String::trim)
//                .collect(Collectors.toList());
//    }

}
