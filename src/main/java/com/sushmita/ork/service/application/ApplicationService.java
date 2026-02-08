package com.sushmita.ork.service.application;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.dtos.ApplicationRequestDto;
import com.sushmita.ork.entity.*;
import com.sushmita.ork.enums.Action;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.enums.StageStatus;
import com.sushmita.ork.service.appStage.AppStageService;
import com.sushmita.ork.service.applicationInfo.ApplicationInfoService;
import com.sushmita.ork.service.user.OrkUserDetailService;
import com.sushmita.ork.service.vacancy.VacancyService;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.*;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Service
public class ApplicationService {
    private final AuthService authService;
    private final ApplicationInfoService applicationInfoService;
    private final VacancyService vacancyService;
    private final ApplicationRepository applicationRepository;
    private final AppStageService appStageService;

    private final OrkUserDetailService orkUserDetailService;


    public ApplicationService(AuthService authService, ApplicationInfoService applicationInfoService, VacancyService vacancyService, ApplicationRepository applicationRepository, AppStageService appStageService, OrkUserDetailService orkUserDetailService) {
        this.authService = authService;
        this.applicationInfoService = applicationInfoService;
        this.vacancyService = vacancyService;
        this.applicationRepository = applicationRepository;
        this.appStageService = appStageService;
        this.orkUserDetailService = orkUserDetailService;
    }

    public Application saveApplication(ApplicationRequestDto applicationRequestDto) {

        // if want to use default, user checks the box and then it populates and user can update it as well. Will be saved as new.
        // if wants to make it default, user has to go to user settings.

        ApplicationUserInfo savedApplicationUserInfo = applicationInfoService.saveApplicationUserInfo(applicationRequestDto);

//        save document todo

        Vacancy vacancy = vacancyService.getVacancyById(applicationRequestDto.getVacancyId());
        Long recruiterId = vacancy.getCompany().getCreatedBy();
        Long applicantId = authService.getCurrentUserId().orElseThrow(() -> new NullPointerException("Error"));
        AppStage savedStage = appStageService.saveDefaultStage(recruiterId, applicantId);

        Application application = Application.builder()
                .professionChoice(applicationRequestDto.getProfessionChoice())
                .companyChoice(applicationRequestDto.getCompanyChoice())
                .uniqueQualities(applicationRequestDto.getUniqueQualities())
                .applicationUserInfoId(savedApplicationUserInfo)
                .userId(applicantId)
                .vacancy(vacancy)
                .submittedDate(new Date())
                .stageStatus(StageStatus.PREVIEW_BY_RECRUITER)
                .recentStage(savedStage).build();

        try {
            return applicationRepository.save(application);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save Application");
        }
    }

    public List<Application> getAll() throws ServiceNotFoundException {

//        Applicant -> created by
//        Recruiter -> vacancy created
//        HR/Director -> to User

        RoleType roleType = authService.getCurrentRoleType();
        Long currentId = authService.getCurrentUserId().orElseThrow(() -> new NullPointerException("Error"));

        switch (roleType) {
            case APPLICANT:
                return applicationRepository.findAllByUserId(currentId);

            case RECRUITER:
                return applicationRepository.findAllByVacancyCreatedBy(currentId);

            default:
//            case RoleType.HR :
//            case RoleType.DIRECTOR :
                return applicationRepository.findAllByRecentStage_ToUser_Id(currentId);

        }
    }

    public Boolean performAction(Long applicationId, Action action) throws ServiceNotFoundException {
        //get application
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("No valid application"));

        //check application
        if (application.getStageStatus() == StageStatus.APPROVE) {
            throw new ServiceNotFoundException("Cannot perform this action");
        }

        OrkUser currentUser = authService.getCurrentUser().orElseThrow(() -> new NullPointerException("Invalid"));
        RoleType currentRoleType = currentUser.getRole().getName();
        Set<AppStage> oldStagesSet = application.getOldStages();
        List<AppStage> oldStages = new ArrayList<>(oldStagesSet);
        AppStage currentStage = application.getRecentStage();
        RoleType roleType = null;
        StageStatus stageStatus = null;
        AppStage savedStage = null;
        Long recruiterId = currentUser.getId();

        switch (action) {
            case DELETE:
                //TODO
                break;

            case FORWARD:
                if (currentRoleType == RoleType.RECRUITER) {
                    roleType = RoleType.HR;
                    stageStatus = StageStatus.PREVIEW_BY_HR;
                } else if (currentRoleType == RoleType.HR) {
                    roleType = RoleType.DIRECTOR;
                    stageStatus = StageStatus.PREVIEW_BY_DIRECTOR;
                    recruiterId = application.getVacancy().getCreatedBy();
                }

                //save new stage
                AppStage forwardAppStage = AppStage.builder()
                        .action(Action.FORWARD)
                        .fromUser(currentUser)
                        .toUser(orkUserDetailService.getSpecificUserBySpecificRole(roleType, recruiterId))
                        .build();

                savedStage = appStageService.saveStage(forwardAppStage);
                break;


            case BACKWARD:
                
                OrkUser user = null;
                if (currentRoleType == RoleType.DIRECTOR) {
                    roleType = RoleType.HR;
                    stageStatus = StageStatus.PREVIEW_BY_HR;
                    recruiterId = application.getVacancy().getCreatedBy();
                    user = orkUserDetailService.getSpecificUserBySpecificRole(roleType, recruiterId);
                } else if (currentRoleType == RoleType.HR) {
                    roleType = RoleType.RECRUITER;
                    stageStatus = StageStatus.PREVIEW_BY_RECRUITER;
                    recruiterId = application.getVacancy().getCreatedBy();
                    user = orkUserDetailService.getUserById(recruiterId);
                }

                //save new stage
                AppStage backwardAppStage = AppStage.builder()
                        .action(Action.BACKWARD)
                        .fromUser(currentUser)
                        .toUser(user)
                        .build();

                savedStage = appStageService.saveStage(backwardAppStage);
                break;


            case APPROVE:
                stageStatus = StageStatus.APPROVE;

                //save new stage
                AppStage approveAppStage = AppStage.builder()
                        .action(Action.APPROVE)
                        .fromUser(currentUser)
                        .toUser(currentUser)
                        .build();

                savedStage = appStageService.saveStage(approveAppStage);
                break;

        }

        oldStages.add(currentStage);

        //shift stages in application
        Set<AppStage> setOfOldStages = new LinkedHashSet<>(oldStages);
        application.setOldStages(setOfOldStages);

        application.setRecentStage(savedStage);
        application.setStageStatus(stageStatus);

        //update application
        applicationRepository.save(application);

        return true;
    }
}
