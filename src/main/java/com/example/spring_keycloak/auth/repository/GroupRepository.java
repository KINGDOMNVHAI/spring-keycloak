package com.example.spring_keycloak.auth.repository;

import com.example.spring_keycloak.auth.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
