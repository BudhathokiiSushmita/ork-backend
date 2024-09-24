package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-09-24
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

   @Enumerated(EnumType.STRING)
   private RoleType name;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "role_nav_permission", joinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id"),
           inverseJoinColumns =  @JoinColumn(name= "nav_permission_id", referencedColumnName = "id"))
   private List<NavPermission> navPermissions = new ArrayList<>();

}
