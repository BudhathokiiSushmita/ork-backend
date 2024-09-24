package com.sushmita.ork.service.user;

import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

//        OrkRole orkRole = roleService.getByRoleName(registerDto.getRoleName());
//
//        OrkUser user = OrkUser.builder().username(registerDto.getUsername())
//                .password(passwordEncoder.encode(registerDto.getPassword()))
//                .email(registerDto.getEmail())
//                .contactNumber(registerDto.getContactNumber())
////                .orkRole(orkRole)
//                .build();
//        userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
