package com.sushmita.ork.service.role;

import com.sushmita.ork.entity.OrkRole;
import com.sushmita.ork.entity.Roles;
import com.sushmita.ork.enums.Role;
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

    public Set<OrkRole> getAllRole() {
//        return Set.copyOf(roleRepository.findAll());
        return new HashSet<>();
    }

    public OrkRole getByRoleName(String roleName) {
        Role role = Role.valueOf(roleName);
//        return roleRepository.findOrkRoleByRoleName(role);
        return null;
    }

}
