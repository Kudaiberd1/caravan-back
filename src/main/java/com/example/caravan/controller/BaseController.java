package com.example.caravan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/base")
public class BaseController {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('client_admin')") // todo: create enum for roles or do something else like in "inlive" or "wonder"
    public String admin() {
        return "Hello Admin";
    }
}
