package com.sushmita.ork.service.navPermission;

import com.sushmita.ork.entity.NavPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */
@Service
public class NavPermissionService {

   @Autowired
   private NavPermissionRepository navPermissionRepository;

   public Set<NavPermission> getAllNavPermission() {
      return Set.copyOf(navPermissionRepository.findAll());
   }
}
