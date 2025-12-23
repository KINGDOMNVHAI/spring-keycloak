package com.example.spring_keycloak.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "cre_dt", nullable = false, updatable = false)
    private LocalDateTime creDt;

    @Column(name = "cre_by", nullable = false)
    private String creBy;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Column(name = "mod_by")
    private String modBy;

    @Column(name = "del_dt")
    private LocalDateTime delDt;

    @Column(name = "del_by")
    private String delBy;

    @PrePersist
    protected void onCreate() {
        this.creDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modDt = LocalDateTime.now();
    }
}