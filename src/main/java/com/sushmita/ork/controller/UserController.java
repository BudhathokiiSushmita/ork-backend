package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.dtos.AuthResponseDto;
import com.sushmita.ork.dtos.UserDto;
import com.sushmita.ork.dtos.UserRequestDto;
import com.sushmita.ork.jwtConfig.JwtGenerator;
import com.sushmita.ork.service.user.OrkUserDetailService;
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

import java.util.List;

import static com.sushmita.ork.constants.APIConstants.USER_API;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@RestController
@RequestMapping(USER_API)
public class UserController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    private OrkUserDetailService orkUserDetailService;


    @Autowired
    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, OrkUserDetailService orkUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.orkUserDetailService = orkUserDetailService;
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

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
//        try{
//            OrkUser user = new OrkUser();
//            user.setUsername(registerDto.getUsername());
//            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//
//            Role role = roleRepository.findByName(RoleType.ADMIN);
//
//            user.setRole(role);
//
//            userRepository.save(user);
//            return CustomResponse.getSuccessResponse("Successfully registered", null);
//        } catch (Exception e) {
//            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try{
            //password encoder cannot be used in Service because of circular dependency.
            // TODO password needs to be random and sent to email. NEED TO WORK ON THIS
            String encodedPassword = passwordEncoder.encode("Admin@123456");
            orkUserDetailService.createUser(userDto, encodedPassword);
            return CustomResponse.getSuccessResponse("Successfully created", null);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUser() {
        try{
            List<UserDto> userList = orkUserDetailService.getAllUser();
            return CustomResponse.getSuccessResponse("Successfully fetched", userList);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.NOT_FOUND);
        }
    }
}
