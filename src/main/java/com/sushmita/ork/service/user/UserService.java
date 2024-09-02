package com.sushmita.ork.service.user;

import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.entity.Company;
import com.sushmita.ork.entity.OrkRole;
import com.sushmita.ork.entity.User;
import com.sushmita.ork.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.sushmita.ork.enums.Role.APPLICANT;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public void saveUser(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameNotFoundException("Username not available");
        }

//        Company company = null;
//
//        if(!registerDto.getRoleName().equals(APPLICANT)) {
//            //get company and
//            company
//        }

        OrkRole orkRole = roleService.getByRoleName(registerDto.getRoleName());

        User user = User.builder().username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .email(registerDto.getEmail())
                .contactNumber(registerDto.getContactNumber())
                .orkRole(orkRole)
                .build();
        userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
