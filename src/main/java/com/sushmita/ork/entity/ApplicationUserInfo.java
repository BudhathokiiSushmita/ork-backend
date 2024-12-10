package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-11-25
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationUserInfo extends BaseEntity {

   private String firstName;
   private String lastName;
   private String address;
   private String country;

   @OneToMany
   private List<WorkExperience> workExperienceList = new ArrayList<>();

   @OneToMany
   private List<EducationQualification> educationQualificationList = new ArrayList<>();
}
