package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-23
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrkRole extends BaseEntity   {

   @Enumerated(EnumType.STRING)
   private Role roleName;

   // For dynamic navigation
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "ork_role_nav_permission",
           joinColumns = @JoinColumn(name = "ork_role_id"),
           inverseJoinColumns = @JoinColumn(name = "nav_permission_id"))
   private Set<NavPermission> navPermissions;
}
