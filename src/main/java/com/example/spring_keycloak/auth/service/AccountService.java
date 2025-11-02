package com.example.spring_keycloak.auth.service;

import com.example.spring_keycloak.auth.entity.Account;
import com.example.spring_keycloak.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public boolean validate(String username, String password) {
        Optional<Account> account = repo.findByUsername(username);
        if (account.isPresent()) return true;
        return false;
    }
}
