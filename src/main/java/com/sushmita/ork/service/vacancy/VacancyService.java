package com.sushmita.ork.service.vacancy;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.entity.Company;
import com.sushmita.ork.entity.Vacancy;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.service.company.CompanyService;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-11-04
 */

@Service
public class VacancyService {

    private final CompanyService companyService;
    private final VacancyRepository vacancyRepository;

    private final AuthService authService;

    public VacancyService(CompanyService companyService, VacancyRepository vacancyRepository, AuthService authService) {
        this.companyService = companyService;
        this.vacancyRepository = vacancyRepository;
        this.authService = authService;
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
}
