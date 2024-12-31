package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.Image;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-12-31
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrkFile extends BaseEntity {

   private String name;
   private String filePath;
   private String type; //doc/image
   private Image fileType; //specific type - sector/application

}
