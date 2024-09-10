package com.sushmita.ork.service.navPermission;

import com.sushmita.ork.entity.NavPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@Repository
public interface NavPermissionRepository extends JpaRepository<NavPermission, Long> {
}
