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

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> getAllRole() {
        return Set.copyOf(roleRepository.findAll());
    }

    public Role getByRoleName(String roleName) {
        RoleType roleType = RoleType.valueOf(roleName);
//        return roleRepository.findOrkRoleByRoleName(roleType);
        return null;
    }

    public List<NavPermission> getAllNavPermissionsByRole() throws ServiceNotFoundException {
        return roleRepository.findByName(AuthService.getCurrentRoleType()).getNavPermissions();
    }
}
