package com.sushmita.ork.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */
public class OrkUserDetail implements UserDetails {

    private final User user;

    public OrkUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Spring will use list of authorities of each user
        return user.getOrkRole().getRoleName().getAuthorities();
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
