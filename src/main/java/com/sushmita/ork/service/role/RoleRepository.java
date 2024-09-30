package com.sushmita.ork.service.role;

import com.sushmita.ork.entity.Role;
import com.sushmita.ork.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType name);
}
