package com.sushmita.ork.service.educationQualification;

import com.sushmita.ork.entity.EducationQualification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Service
public class EducationQualificationService {

    private final EducationQualificationRepository educationQualificationRepository;

    public EducationQualificationService(EducationQualificationRepository educationQualificationRepository) {
        this.educationQualificationRepository = educationQualificationRepository;
    }

    public List<EducationQualification> saveAll(List<EducationQualification> educationQualificationList) {
        try {
            return educationQualificationRepository.saveAll(educationQualificationList);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save education qualification.");
        }
    }
}
