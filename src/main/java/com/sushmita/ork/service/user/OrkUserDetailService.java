package com.sushmita.ork.service.user;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.dtos.UserDto;
import com.sushmita.ork.entity.OrkUser;
import com.sushmita.ork.entity.Role;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.mapper.UserMapper;
import com.sushmita.ork.service.role.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.*;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Service
public class OrkUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;

    private AuthService authService;

    public OrkUserDetailService(UserRepository userRepository, RoleService roleService, AuthService authService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OrkUser user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    public OrkUser register(RegisterDto registerDto) {
//        CustomUser user = new CustomUser();
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        return null;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Role role) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName().toString());
        return Set.of(grantedAuthority);
    }

    public void createUser(UserDto userDto, String encodedPassword) {
        OrkUser orkUser;
        userDto.setActualRole(roleService.getByRoleName(userDto.getRole()));

        try {
            orkUser = UserMapper.INSTANCE.userDtoToUser(userDto);
        } catch (Exception e) {
            throw new ServiceConfigurationError("Mapping failed");
        }

        orkUser.setPassword(encodedPassword);
        orkUser.setCreatedBy(authService.getCurrentUserId().get());
        userRepository.save(orkUser);
    }

    public List<UserDto> getAllUser() throws ServiceNotFoundException {
        RoleType currentRoleType = authService.getCurrentRoleType();
        List<OrkUser> userList;
        if(currentRoleType == RoleType.ADMIN) {
           userList = userRepository.findAll().stream().toList();
        } else {
            userList = userRepository.getAllByCreatedBy(authService.getCurrentUserId().get());
        }
        return userList.stream().map(UserMapper.INSTANCE::mapEntityToDto).toList();
    }

    public Optional<Long> getCurrentUserId() {
        OrkUser user = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return Optional.of(user.getId());
    }

    public OrkUser getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
