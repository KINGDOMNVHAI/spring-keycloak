package com.example.spring_keycloak.auth.repository;

import com.example.spring_keycloak.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccUsr(String accUsr);
}
