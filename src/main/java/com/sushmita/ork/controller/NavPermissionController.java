package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.NavPermission;
import com.sushmita.ork.service.navPermission.NavPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.sushmita.ork.constants.APIConstants.NAV_PERMISSION_API;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */

@RestController
@RequestMapping(NAV_PERMISSION_API)
public class NavPermissionController {

    @Autowired
    private NavPermissionService navPermissionService;

   @GetMapping("/all")
    public ResponseEntity<?> getAllNavPermission() {
       try {
           Set<NavPermission> navPermissions = navPermissionService.getAllNavPermission();
           return CustomResponse.getSuccessResponse("Successfully fetched Navigation permissions", navPermissions);
       } catch (Exception e) {
           return CustomResponse.getErrorResponse("Cannot fetch data", "", HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}
