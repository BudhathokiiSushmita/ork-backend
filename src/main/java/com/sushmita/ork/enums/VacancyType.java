package com.sushmita.ork.enums;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */
public enum VacancyType {

   FULL_TIME("Full Time"),
   PART_TIME("Part Time");

   private final String enumValue;

   VacancyType(String value) {
       this.enumValue = value;
   }

   public String getValue() {
      return this.enumValue;
   }
}
