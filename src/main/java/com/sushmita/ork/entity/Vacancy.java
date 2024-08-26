package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy extends BaseEntity   {

   private String title;
   private String description;
   private String educationQualification;
   private String requirement;
   private Date startDate;
   private Date deadline;
   private Date createdAt;
   private String salary;
}
