package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.Company;
import com.sushmita.ork.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sushmita.ork.constants.APIConstants.COMPANY_API;

/**
 * @author Sushmita Budhathoki on 2024-10-22
 */

@RestController
@RequestMapping(COMPANY_API)
public class CompanyController {

   private final CompanyService companyService;

   @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/check-by-created")
    public ResponseEntity<?> checkIfCompanyExistsByCurrentUser() {
       try {
           return CustomResponse.getSuccessResponse("", companyService.checkIfCompanyExistsByCurrentUser());
       } catch (Exception e) {
           return CustomResponse.getErrorResponse("No company is found", "", HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping()
    public ResponseEntity<?> saveCompany(@RequestBody Company company) {
        try {
            return CustomResponse.getSuccessResponse("Created successfully", companyService.createCompany(company));
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't create company", "", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-all-companies")
    public ResponseEntity<?> getAllCompany() {
        try {
            return CustomResponse.getSuccessResponse("Fetched successfully", companyService.getAllCompany());
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't get company", "", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
