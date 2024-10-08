package com.sushmita.ork.service.user;

import com.sushmita.ork.dtos.RegisterDto;
import com.sushmita.ork.entity.OrkUser;
import com.sushmita.ork.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Service
public class OrkUserDetailService implements UserDetailsService {

//    @Autowired
    private UserRepository userRepository;

    @Autowired
    public OrkUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
