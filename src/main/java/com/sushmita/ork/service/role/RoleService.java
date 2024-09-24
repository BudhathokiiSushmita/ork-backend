package com.sushmita.ork.service.role;

import com.sushmita.ork.entity.Role;
import com.sushmita.ork.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> getAllRole() {
//        return Set.copyOf(roleRepository.findAll());
        return new HashSet<>();
    }

    public Role getByRoleName(String roleName) {
        RoleType roleType = RoleType.valueOf(roleName);
//        return roleRepository.findOrkRoleByRoleName(roleType);
        return null;
    }

}
