package com.sushmita.ork.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-08-28
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

   private String message;
   private Object body;

   public static CustomResponse getResponse(String message, Object object) {
      return new CustomResponse(message, object);
   }

}
