package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.constants.APIConstants;
import com.sushmita.ork.dtos.ApplicationRequestDto;
import com.sushmita.ork.service.application.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@RestController
@RequestMapping(APIConstants.APPLICATION_API)
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping()
    public ResponseEntity<?> saveApplication(@RequestBody ApplicationRequestDto applicationRequestDto) {
        try {
            return CustomResponse.getSuccessResponse("Successfully saved", applicationService.saveApplication(applicationRequestDto));
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't save application", "", HttpStatus.BAD_REQUEST);
        }
    }
}
