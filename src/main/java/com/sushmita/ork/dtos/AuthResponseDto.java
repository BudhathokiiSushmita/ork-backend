package com.sushmita.ork.dtos;

import lombok.Data;

/**
 * @author Sushmita Budhathoki on 2024-08-30
 */

@Data
public class AuthResponseDto {

   private String accessToken;
   private String tokenType = "Bearer ";

   public AuthResponseDto(String accessToken) {
      this.accessToken = accessToken;
   }
}
