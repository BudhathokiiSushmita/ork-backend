package com.sushmita.ork.dtos;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.entity.EducationQualification;
import com.sushmita.ork.entity.WorkExperience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-12-09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto extends BaseEntity {

   //personal data
   private String firstName;
   private String lastName;
   private String address;
   private String country;
   private List<WorkExperience> workExperienceList = new ArrayList<>();
   private List<EducationQualification> educationQualificationList = new ArrayList<>();

   //questionnaire
   private String professionChoice;
   private String companyChoice;
   private String uniqueQualities;

   //docs  todo

   private Long vacancyId;
}
