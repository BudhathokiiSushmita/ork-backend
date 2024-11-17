package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.Vacancy;
import com.sushmita.ork.service.vacancy.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sushmita.ork.constants.APIConstants.VACANCY_API;

/**
 * @author Sushmita Budhathoki on 2024-11-04
 */

@RestController
@RequestMapping(VACANCY_API)
public class VacancyController {

    private final VacancyService vacancyService;


    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @PostMapping()
    public ResponseEntity<?> saveVacancy(@RequestBody Vacancy vacancy) {
        try {
            return CustomResponse.getSuccessResponse("Successfully saved Vacancy", vacancyService.saveVacancy(vacancy));
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Cannot save vacancy", "", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-all-vacancies")
    public ResponseEntity<?> getAllVacancy() {
        try {
            return CustomResponse.getSuccessResponse("Successfully fetched all companies", vacancyService.getAllVacancies());
        } catch (Exception e) {
            System.out.println("exception" + e);
            return CustomResponse.getErrorResponse("Cannot get vacancies", "", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllVacanciesId(@PathVariable("id") Long id) {
        try {
            List<Vacancy> vacancyList = vacancyService.getAllVacanciesBySectorId(id);
            return CustomResponse.getSuccessResponse("Fetched successfully", vacancyList);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
        }
    }
}
