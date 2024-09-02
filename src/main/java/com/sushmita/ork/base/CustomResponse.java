package com.sushmita.ork.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

   //status is always 200 so not in parameter
   public static ResponseEntity<?> getSuccessResponse(String message, Object object) {
      return new ResponseEntity<>(new CustomResponse(message, object), HttpStatus.OK);
   }

   public static ResponseEntity<?> getErrorResponse(String message, Object object, HttpStatus httpStatus) {
      return new ResponseEntity<>(new CustomResponse(message, object), httpStatus);
   }

}
