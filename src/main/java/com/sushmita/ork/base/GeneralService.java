package com.sushmita.ork.base;

import com.sushmita.ork.enums.TrueFalse;
import com.sushmita.ork.enums.VacancyType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.sushmita.ork.constants.APIConstants.GENERAL_API;

/**
 * @author Sushmita Budhathoki on 2024-11-07
 */

@RestController
@RequestMapping(GENERAL_API)
public class GeneralService {

    @GetMapping("/get-vacancies")
    public ResponseEntity<?> getVacancyEnumValues() {
        try {
            List<String> lists = Arrays.stream(VacancyType.values()).map(VacancyType::getValue).toList();
            return CustomResponse.getSuccessResponse("Successfully fetched", lists);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Cannot get", "", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-booleans")
    public ResponseEntity<?> getBooleanEnumValues() {
        try {
            List<String> lists = Arrays.stream(TrueFalse.values()).map(TrueFalse::getValue).toList();
            return CustomResponse.getSuccessResponse("Successfully fetched", lists);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Cannot get", "", HttpStatus.NOT_FOUND);
        }
    }
}
