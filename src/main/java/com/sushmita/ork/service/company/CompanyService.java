package com.sushmita.ork.service.company;

import com.sushmita.ork.base.AuthService;
import com.sushmita.ork.entity.Company;
import com.sushmita.ork.enums.RoleType;
import com.sushmita.ork.service.user.OrkUserDetailService;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;

/**
 * @author Sushmita Budhathoki on 2024-10-22
 */

@Service
public class CompanyService {

   private CompanyRepository companyRepository;

   private OrkUserDetailService orkUserDetailService;

    public CompanyService(CompanyRepository companyRepository, OrkUserDetailService orkUserDetailService) {
        this.companyRepository = companyRepository;
        this.orkUserDetailService = orkUserDetailService;
    }

    public Company getCompanyCreatedByCurrentUser() {
        Long currentId = orkUserDetailService.getCurrentUserId().orElseThrow(() -> new NullPointerException("Invalid ID"));
        return companyRepository.findCompanyByCreatedBy(currentId).orElseThrow(() -> new NullPointerException("No Company found"));
    }

    public Boolean checkIfCompanyExistsByCurrentUser() throws ServiceNotFoundException {
        // Only check if the current user is RECRUITER
        if (AuthService.getCurrentRoleType().equals(RoleType.RECRUITER)) {
            try {
                //FRONT: go to Dashboard if it is true
                Company company = getCompanyCreatedByCurrentUser();
                return true;
            } catch (Exception e) {
                //FRONT: Pop up company create form if it is false
                System.out.println("ORK MESSAGE: " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    public Company createCompany(Company company) {
        company.setCreatedBy(orkUserDetailService.getCurrentUserId().orElseThrow(() -> new NullPointerException("No User found")));
        return companyRepository.save(company);
    }
}
