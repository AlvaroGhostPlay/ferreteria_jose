package com.alvaro.springcloud.msvc.oauth.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws Exception {
        response.sendRedirect("http://localhost:4200/login");
    }
}
