package com.sushmita.ork.controller;

import com.sushmita.ork.service.OrkUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private OrkUserDetailService orkUserDetailService;

    @GetMapping("/{username}")
    public UserDetails getUserDetails(@PathVariable String username) {
        return orkUserDetailService.loadUserByUsername(username);
    }
}
