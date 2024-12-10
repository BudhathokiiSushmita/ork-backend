package com.sushmita.ork.service.appStage;

import com.sushmita.ork.entity.AppStage;
import com.sushmita.ork.entity.OrkUser;
import com.sushmita.ork.enums.StageStatus;
import com.sushmita.ork.service.user.OrkUserDetailService;
import org.springframework.stereotype.Service;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Service
public class AppStageService {

   private final OrkUserDetailService orkUserDetailService;
   private final AppStageRepository appStageRepository;

    public AppStageService(OrkUserDetailService orkUserDetailService, AppStageRepository appStageRepository) {
        this.orkUserDetailService = orkUserDetailService;
        this.appStageRepository = appStageRepository;
    }

    public AppStage saveDefaultStage(Long recruiterId, Long applicantId) {
      try {
         OrkUser recruiter = orkUserDetailService.getUserById(recruiterId);
         OrkUser applicant = orkUserDetailService.getUserById(applicantId);
         AppStage appStage = AppStage.builder()
                 .stageStatus(StageStatus.SUBMITTED)
                 .fromUser(applicant)
                 .toUser(recruiter)
                 .build();

         return appStageRepository.save(appStage);
      } catch (Exception e) {
         throw new RuntimeException("Couldn't save stage");
      }
   }
}
