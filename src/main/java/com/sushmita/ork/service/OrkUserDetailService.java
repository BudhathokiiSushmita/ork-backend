package com.sushmita.ork.service;

import com.sushmita.ork.entity.OrkUserDetail;
import com.sushmita.ork.entity.User;
import com.sushmita.ork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Service
public class OrkUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new OrkUserDetail(user);
    }
}
