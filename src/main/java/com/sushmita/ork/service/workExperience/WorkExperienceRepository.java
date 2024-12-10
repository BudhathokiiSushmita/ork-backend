package com.sushmita.ork.service.workExperience;

import com.sushmita.ork.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
}
