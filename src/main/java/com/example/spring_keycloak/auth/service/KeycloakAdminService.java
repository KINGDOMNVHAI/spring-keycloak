package com.example.spring_keycloak.auth.service;

import com.example.spring_keycloak.auth.entity.Group;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakAdminService {

    private Keycloak keycloak;

    private final String serverUrl = "http://localhost:8080";
    private final String realm = "demo-realm";
    private final String clientId = "admin-cli";
    private final String username = "admin";
    private final String password = "admin";

    @PostConstruct
    public void init() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .clientId(clientId)
                .username(username)
                .password(password)
                .build();
    }

    /*
    * Insert group on Keycloak
    * */
    public void syncGroupWithKeycloak(Group group) {
        GroupRepresentation groupRep = new GroupRepresentation();
        groupRep.setName(group.getName());

        Response response = keycloak.realm(realm).groups().add(groupRep);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Can't create group on Keycloak: " + response.getStatus());
        }

        String location = response.getLocation().toString();
        String groupId = location.substring(location.lastIndexOf("/") + 1);

        // role to group
        List<String> roleNames = parseRoles(group.getKcRoles());
        List<RoleRepresentation> roles = keycloak.realm(realm).roles().list().stream()
                .filter(r -> roleNames.contains(r.getName()))
                .collect(Collectors.toList());

        keycloak.realm(realm).groups().group(groupId).roles().realmLevel().add(roles);
    }

    private List<String> parseRoles(String kcRoles) {
        return kcRoles.replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",")
                .length == 0 ? List.of() :
                List.of(kcRoles.replace("[", "").replace("]", "").replace(" ", "").split(","));
    }
}
