package com.sushmita.ork.dtos;

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
//   private String roleName; //use this to fetch role and then attach company
   private String email;
   private String contactNumber;

}
