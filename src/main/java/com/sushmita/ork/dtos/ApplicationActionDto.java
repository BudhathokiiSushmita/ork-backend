package com.sushmita.ork.dtos;

import com.sushmita.ork.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-12-22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationActionDto {

   private Long applicationId;
   private Action action;
}
