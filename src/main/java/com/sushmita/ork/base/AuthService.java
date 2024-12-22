package com.sushmita.ork.base;

import com.sushmita.ork.entity.OrkUser;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.service.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Sushmita Budhathoki on 2024-09-30
 */
@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RoleType getCurrentRoleType() throws ServiceNotFoundException {
        try {
            List<? extends GrantedAuthority> lists = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
            return RoleType.valueOf(lists.getFirst().toString());
        } catch (Exception e) {
            throw new ServiceNotFoundException("Not authorized");
        }
    }

    public Optional<Long> getCurrentUserId() {
        OrkUser user = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return Optional.of(user.getId());
    }

    public Optional<OrkUser> getCurrentUser() {
        OrkUser user = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return Optional.of(user);
    }
}
