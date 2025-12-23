package com.example.spring_keycloak.auth.dto.request;

public class GroupRequest {
    private Long id;
    private String name;
    private String kcRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKcRoles() {
        return kcRoles;
    }

    public void setKcRoles(String kcRoles) {
        this.kcRoles = kcRoles;
    }
}
