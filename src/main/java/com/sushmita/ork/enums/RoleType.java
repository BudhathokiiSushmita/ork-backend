package com.sushmita.ork.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-23
 */

@RequiredArgsConstructor
public enum RoleType {

   //linking RoleType and ActionPermissions
   ADMIN(Set.of(
           ActionPermission.ADMIN_READ,
           ActionPermission.ADMIN_WRITE,
           ActionPermission.ADMIN_UPDATE,
           ActionPermission.ADMIN_DELETE,
           ActionPermission.RECRUITER_READ,
           ActionPermission.HR_READ,
           ActionPermission.DIRECTOR_READ,
           ActionPermission.APPLICANT_READ
   )),

   RECRUITER(Set.of(
           ActionPermission.RECRUITER_READ,
           ActionPermission.RECRUITER_WRITE,
           ActionPermission.RECRUITER_DELETE,
           ActionPermission.RECRUITER_UPDATE
   )),
   HR(Set.of(
           ActionPermission.HR_READ,
           ActionPermission.HR_WRITE,
           ActionPermission.HR_DELETE,
           ActionPermission.HR_UPDATE
   )),
   DIRECTOR(Set.of(
           ActionPermission.DIRECTOR_READ,
           ActionPermission.DIRECTOR_WRITE,
           ActionPermission.DIRECTOR_DELETE,
           ActionPermission.DIRECTOR_UPDATE
   )),
   APPLICANT(Set.of(
           ActionPermission.APPLICANT_READ,
           ActionPermission.APPLICANT_WRITE,
           ActionPermission.APPLICANT_DELETE,
           ActionPermission.APPLICANT_UPDATE
   ));

   @Getter
   private final Set<ActionPermission> actionPermissions;

   public List<SimpleGrantedAuthority> getAuthorities() {
      var authorities = getActionPermissions()
              .stream()
              .map(f -> new SimpleGrantedAuthority(f.name()))
              .toList();

      //this adds action permission of current chosen role
      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
      return authorities;
   }
}
