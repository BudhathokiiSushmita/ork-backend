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

   public AuthResponseDto(String accessToken, String username) {
      this.accessToken = accessToken;
      this.username = username;
   }
}
