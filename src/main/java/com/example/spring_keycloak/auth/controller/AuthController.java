package com.example.spring_keycloak.auth.controller;

import com.example.spring_keycloak.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (accountService.validate(username, password)) {
            return Map.of("message", "Login success", "username", username);
        } else {
            return Map.of("message", "Invalid credentials");
        }
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> loginKeycloak(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        String tokenUrl = "http://localhost:8686/realms/demo-realm/protocol/openid-connect/token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "demo-client");
        params.add("username", username);
        params.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "Invalid username or password",
                            "details", e.getResponseBodyAsString()
                    ));
        }
    }

    private String getFormData(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) sb.append("&");
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    @GetMapping("/public/profile")
    public Map<String, String> profile(@RequestHeader("Authorization") String auth) {
        return Map.of("message", "Access granted", "token", auth);
    }
}
