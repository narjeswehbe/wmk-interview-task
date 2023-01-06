package com.narjes.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/hello")
    public String index()
    {
        return "Hello";
    }
}
