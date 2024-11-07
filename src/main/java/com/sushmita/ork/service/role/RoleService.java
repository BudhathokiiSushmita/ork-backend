package com.sushmita.ork.service.role;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.entity.NavPermission;
import com.sushmita.ork.entity.Role;
import com.sushmita.ork.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private AuthService authService;

    public Set<Role> getAllRoleAndNavPermission() {
        return Set.copyOf(roleRepository.findAll());
    }

    public Role getByRoleName(RoleType roleName) {
        return roleRepository.findByName(roleName);
    }

    public List<NavPermission> getAllNavPermissionsByRole() throws ServiceNotFoundException {
        return roleRepository.findByName(authService.getCurrentRoleType()).getNavPermissions();
    }

    public List<RoleType> getAllRole() {
        List<Role> roles =  roleRepository.findAll();
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
