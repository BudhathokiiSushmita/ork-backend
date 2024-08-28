package com.sushmita.ork.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-08-28
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomResponse {

   private String message;
   private Object object;

   public static CustomResponse getResponse(String message, Object object) {
      return new CustomResponse(message, object);
   }

}
