package com.sushmita.ork.dtos;

import lombok.Data;

/**
 * @author Sushmita Budhathoki on 2024-08-30
 */

@Data
public class AuthResponseDto {

   private String accessToken;
   private String tokenType = "Bearer ";
   private String username;
   private String roleType;

   public AuthResponseDto(String accessToken, String username, String roleType) {
      this.accessToken = accessToken;
      this.username = username;
      this.roleType = roleType;
   }
}
