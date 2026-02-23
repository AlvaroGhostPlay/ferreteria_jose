package com.alvaro.curso.springcloud.appgateway.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class AppController {

    @GetMapping("/authorized")
    public Map<String, String> authorized(@RequestParam String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        return map;
    }

    @PostMapping("/logout")
    public Map<String, String> logout() {
        return Collections.singletonMap("logout", "Ok");
    }

    @GetMapping("/employee")
    public ResponseEntity<?> employee() {
        return ResponseEntity.ok("employee");
    }

    @PostMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("admin");
    }
}
