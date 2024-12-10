package com.sushmita.ork.service.appStage;

import com.sushmita.ork.entity.AppStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface AppStageRepository extends JpaRepository<AppStage, Long> {
}
