package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.dtos.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @GetMapping("/{username}")
//    public UserDetails getUserDetails(@PathVariable String username) {
//        return orkUserDetailService.loadUserByUsername(username);
//    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody UserRequestDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(CustomResponse.getResponse("successfully logged in", null), HttpStatus.OK);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return new ResponseEntity<>(CustomResponse.getResponse("Invalid username or password", null), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(CustomResponse.getResponse("Authentication failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        // need to optimize this, find out real reason
        try{

//            CustomUser newUser = orkUserDetailService.register(registerDto);
            return new ResponseEntity<>(CustomResponse.getResponse("Successfully registered in", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(CustomResponse.getResponse(e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
