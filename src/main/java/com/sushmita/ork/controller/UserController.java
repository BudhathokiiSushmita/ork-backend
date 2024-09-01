package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.dtos.AuthResponseDto;
import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.dtos.UserRequestDto;
import com.sushmita.ork.entity.User;
import com.sushmita.ork.jwtConfig.JwtGenerator;
import com.sushmita.ork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.sushmita.ork.constants.APIConstants.USER_API;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@RestController
@RequestMapping(USER_API)
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(CustomResponse.getResponse("successfully logged in", new AuthResponseDto(token)), HttpStatus.OK);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return new ResponseEntity<>(CustomResponse.getResponse("Invalid username or password", null), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(CustomResponse.getResponse("Authentication failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try{
            if(userService.existsByUsername(registerDto.getUsername())) {
                return new ResponseEntity<>("Username not available", HttpStatus.BAD_REQUEST);
            }

            User user = User.builder().username(registerDto.getUsername())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .email(registerDto.getEmail())
                    .contactNumber(registerDto.getContactNumber())
                    .orkRole(registerDto.getOrkRole())
                    .build();

            userService.saveUser(user);
            return new ResponseEntity<>(CustomResponse.getResponse("Successfully registered", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(CustomResponse.getResponse(e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
