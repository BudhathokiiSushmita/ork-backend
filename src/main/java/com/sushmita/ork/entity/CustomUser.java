package com.sushmita.ork.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */


import java.util.Collections;

@Data
@NoArgsConstructor
public class CustomUser implements UserDetails {

    private UserEntity user;

    public CustomUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to Spring Security authorities
        return Collections.singletonList(new SimpleGrantedAuthority(""));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
