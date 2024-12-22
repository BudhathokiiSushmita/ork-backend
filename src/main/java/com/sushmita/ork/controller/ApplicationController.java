package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.constants.APIConstants;
import com.sushmita.ork.dtos.ApplicationActionDto;
import com.sushmita.ork.dtos.ApplicationRequestDto;
import com.sushmita.ork.service.application.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseEntity<?> getAllByUser() {
        try {
            return CustomResponse.getSuccessResponse("Successfully saved", applicationService.getAll());
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't save application", "", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/action")
    public ResponseEntity<?> action(@RequestBody ApplicationActionDto applicationActionDto) {
        try {
            return CustomResponse.getSuccessResponse("Successfully saved", applicationService.performAction(applicationActionDto.getApplicationId(), applicationActionDto.getAction()));
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't save application", "", HttpStatus.BAD_REQUEST);
        }
    }
}
