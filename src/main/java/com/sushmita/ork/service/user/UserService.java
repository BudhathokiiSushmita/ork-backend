package com.sushmita.ork.service.user;

import com.sushmita.ork.entity.User;
import com.sushmita.ork.service.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
