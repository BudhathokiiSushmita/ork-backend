package com.sushmita.ork.service.applicationInfo;

import com.sushmita.ork.dtos.ApplicationRequestDto;
import com.sushmita.ork.entity.ApplicationUserInfo;
import com.sushmita.ork.entity.EducationQualification;
import com.sushmita.ork.entity.WorkExperience;
import com.sushmita.ork.service.educationQualification.EducationQualificationService;
import com.sushmita.ork.service.workExperience.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Service
public class ApplicationInfoService {

    private final ApplicationInfoRepository applicationInfoRepository;
    private final WorkExperienceService workExperienceService;
    private final EducationQualificationService educationQualificationService;

    public ApplicationInfoService(ApplicationInfoRepository applicationInfoRepository, WorkExperienceService workExperienceService, EducationQualificationService educationQualificationService) {
        this.applicationInfoRepository = applicationInfoRepository;
        this.workExperienceService = workExperienceService;
        this.educationQualificationService = educationQualificationService;
    }

    public ApplicationUserInfo saveApplicationUserInfo(ApplicationRequestDto applicationRequestDto) {
        try {
            //        save work experience
            List<WorkExperience> savedWE = workExperienceService.saveAll(applicationRequestDto.getWorkExperienceList());

            //        save education qualification
            List<EducationQualification> savedEQ = educationQualificationService.saveAll(applicationRequestDto.getEducationQualificationList());

            //        save application user info
            ApplicationUserInfo applicationUserInfo = ApplicationUserInfo.builder()
                    .firstName(applicationRequestDto.getFirstName())
                    .lastName(applicationRequestDto.getLastName())
                    .address(applicationRequestDto.getAddress())
                    .country(applicationRequestDto.getCountry())
                    .workExperienceList(savedWE)
                    .educationQualificationList(savedEQ)
                    .build();

            return applicationInfoRepository.save(applicationUserInfo);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save Application User Info");
        }
    }
}
