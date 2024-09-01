package com.sushmita.ork.dtos;

import com.sushmita.ork.entity.OrkRole;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-08-29
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

   private String username;
   private String password;
   private OrkRole orkRole;
   private String email;
   private String contactNumber;

}
