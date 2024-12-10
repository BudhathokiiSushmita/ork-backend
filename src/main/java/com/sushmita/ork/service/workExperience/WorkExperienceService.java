package com.sushmita.ork.service.workExperience;

import com.sushmita.ork.entity.WorkExperience;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Service
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public WorkExperienceService(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    public List<WorkExperience> saveAll(List<WorkExperience> workExperienceList) {
        try{
            return workExperienceRepository.saveAll(workExperienceList);
        } catch (Exception e) {
            throw new RuntimeException("Cannot save work experience");
        }
    }
}
