package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String accessAdmin() {
        return "Access Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String accessUser() {
        return "Access User";
    }

    @GetMapping("/invited")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'INVITED')")
    public String accessInvited() {
        return "Access Invited";
    }

}
