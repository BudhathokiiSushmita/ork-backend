package com.sushmita.ork.enums;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */
public enum TrueFalse {

   TRUE("True"),
   FALSE("False");

   private final String enumValue;

   TrueFalse(String value) {
       this.enumValue = value;
   }

   public String getValue() {
      return this.enumValue;
   }
}
