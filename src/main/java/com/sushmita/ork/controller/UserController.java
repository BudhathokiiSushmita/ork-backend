package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.dtos.AuthResponseDto;
import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.dtos.UserRequestDto;
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
            userService.saveUser(registerDto);
            return CustomResponse.getSuccessResponse("Successfully registered", null);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
        }
    }
}
