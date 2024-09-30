package com.sushmita.ork.base;

import com.sushmita.ork.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.management.ServiceNotFoundException;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-09-30
 */

public class AuthService {

   public static RoleType getCurrentRoleType() throws ServiceNotFoundException {
        try {
            List<? extends GrantedAuthority> lists = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
            return RoleType.valueOf(lists.getFirst().toString());
        } catch (Exception e) {
            throw new ServiceNotFoundException("Not authorized");
        }
    }
}
