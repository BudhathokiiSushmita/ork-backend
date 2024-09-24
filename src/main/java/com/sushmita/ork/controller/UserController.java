package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.dtos.AuthResponseDto;
import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.dtos.UserRequestDto;
import com.sushmita.ork.entity.Roles;
import com.sushmita.ork.entity.UserEntity;
import com.sushmita.ork.jwtConfig.JwtGenerator;
import com.sushmita.ork.service.role.RoleRepository;
import com.sushmita.ork.service.user.UserRepository;
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

import java.util.Collection;
import java.util.Collections;

import static com.sushmita.ork.constants.APIConstants.USER_API;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@RestController
@RequestMapping(USER_API)
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;


    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody UserRequestDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return CustomResponse.getSuccessResponse("successfully logged in", new AuthResponseDto(token));
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return CustomResponse.getErrorResponse("Invalid username or password", null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Authentication failed", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try{
            UserEntity user = new UserEntity();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Roles roles = roleRepository.findByName("USER").get();

            user.setRoles(Collections.singletonList(roles));

            userRepository.save(user);
            return CustomResponse.getSuccessResponse("Successfully registered", null);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
        }
    }
}
