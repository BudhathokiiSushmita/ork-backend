package com.sushmita.ork.service.role;

import com.sushmita.ork.entity.OrkRole;
import com.sushmita.ork.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Repository
public interface RoleRepository extends JpaRepository<OrkRole, Long> {

    OrkRole findOrkRoleByRoleName(Role roleName);
}
