package com.sushmita.ork.service.vacancy;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.dtos.VacancyDto;
import com.sushmita.ork.entity.Company;
import com.sushmita.ork.entity.Vacancy;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.mapper.VacancyMapper;
import com.sushmita.ork.service.application.ApplicationRepository;
import com.sushmita.ork.service.company.CompanyService;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Sushmita Budhathoki on 2024-11-04
 */

@Service
public class VacancyService {

    private final CompanyService companyService;
    private final VacancyRepository vacancyRepository;

    private final AuthService authService;

    private final ApplicationRepository applicationRepository;

    private final VacancyMapper vacancyMapper;

    public VacancyService(CompanyService companyService, VacancyRepository vacancyRepository, AuthService authService, ApplicationRepository applicationRepository, VacancyMapper vacancyMapper) {
        this.companyService = companyService;
        this.vacancyRepository = vacancyRepository;
        this.authService = authService;
        this.applicationRepository = applicationRepository;
        this.vacancyMapper = vacancyMapper;
    }

    public Vacancy saveVacancy(Vacancy vacancy) {
        Company company = companyService.getCompanyCreatedByCurrentUser();

        vacancy.setCreatedBy(company.getCreatedBy());
        vacancy.setCompany(company);
        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getAllVacancies() throws ServiceNotFoundException {
        RoleType roleType = authService.getCurrentRoleType();

        if (roleType == RoleType.ADMIN) {
            return vacancyRepository.findAll();
        } else {
            return vacancyRepository.getVacanciesByCreatedBy(authService.getCurrentUserId().get());
        }
    }

    public List<VacancyDto> getAllVacanciesBySectorId(Long sectorId) {
        //if logged in, check if the user has already applied and if yes "apply now" > "applied"

        //vacancy id[] by sector
        //this is final if not logged in
        List<VacancyDto> vacancyDtoList =
                vacancyMapper.mapEntitiesToDtos(
                        vacancyRepository.getVacanciesBySectorId(sectorId)
                );

        //get all the vacancies that has been applied by current user
        try {
            authService.getCurrentUserId().ifPresent(userId -> {

                //if application[] contains vid -> get vid[]
                List<Long> appliedVacancyIdList =
                        applicationRepository.findVacancyIdsByUserId(Optional.of(userId));

                vacancyDtoList.forEach(f ->
                        f.setIsApplied(
                                appliedVacancyIdList.contains(f.getId())
                        )
                );
            });
            return vacancyDtoList;
        } catch (Exception e) {
            return vacancyDtoList;
        }
    }

    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id).orElseThrow(() -> new NullPointerException("Couldn't find vacancy"));
    }
}
