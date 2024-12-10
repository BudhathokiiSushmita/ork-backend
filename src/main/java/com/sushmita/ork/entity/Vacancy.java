package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.VacancyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Vacancy extends BaseEntity {

   private String title;
   private VacancyType vacancyType;
   private Long positionNumber;

   @Column(columnDefinition = "LONGTEXT")
   private String description;

   @Column(columnDefinition = "LONGTEXT")
   private String qualification;

   @Column(columnDefinition = "LONGTEXT")
   private String requirement;

   private Date startDate;
   private Date deadline;
   private Date createdAt;
   private String salaryRange;
   private String applicationProcedure;
   private String documentRequirement;
   private Boolean isPaidPosition;

   @ManyToOne
   private Company company;

   @ManyToOne
   private Sector sector;
}
