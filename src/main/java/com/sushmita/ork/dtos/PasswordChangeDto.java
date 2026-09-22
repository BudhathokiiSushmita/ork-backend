package com.sushmita.ork.dtos;

import com.sushmita.ork.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2026-09-21
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordChangeDto {

   private String currentPassword;
   private String newPassword;
}
