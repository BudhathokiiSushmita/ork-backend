package com.sushmita.ork.service.application;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.dtos.ApplicationRequestDto;
import com.sushmita.ork.entity.*;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.service.appStage.AppStageService;
import com.sushmita.ork.service.applicationInfo.ApplicationInfoService;
import com.sushmita.ork.service.vacancy.VacancyService;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.Date;
import java.util.List;

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

    public ApplicationService(AuthService authService, ApplicationInfoService applicationInfoService, VacancyService vacancyService, ApplicationRepository applicationRepository, AppStageService appStageService) {
        this.authService = authService;
        this.applicationInfoService = applicationInfoService;
        this.vacancyService = vacancyService;
        this.applicationRepository = applicationRepository;
        this.appStageService = appStageService;
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
            case RoleType.APPLICANT :
                return applicationRepository.findAllByUserId(currentId);

            case RoleType.RECRUITER :
                return applicationRepository.findAllByVacancyCreatedBy(currentId);

            default:
//            case RoleType.HR :
//            case RoleType.DIRECTOR :
                return applicationRepository.findAllByUserId(currentId);

        }
    }
}
