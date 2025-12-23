package com.example.spring_keycloak.auth.entity;

import com.example.spring_keycloak.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Integer accId;

    @Column(name = "acc_usr", unique = true, nullable = false)
    private String accUsr;

    @Column(name = "acc_pass", nullable = false)
    private String accPass;

    @Column(name = "pass_miss_cnt", nullable = false)
    private Integer passMissCnt = 0;

    @Column(name = "acc_role", nullable = false)
    private String accRole;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "join_dt")
    private LocalDateTime joinDt;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gend_cd")
    private String gendCd;

    @Column(name = "acc_enable", nullable = false)
    private Boolean accEnable;
}
