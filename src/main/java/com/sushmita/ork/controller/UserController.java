package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.User;
import com.sushmita.ork.service.OrkUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity authenticate(@RequestBody User user) {
        try{
            User existingUser = orkUserDetailService.authenticate(user);
            return new ResponseEntity(CustomResponse.getResponse("Successfully logged in", existingUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(CustomResponse.getResponse(e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
