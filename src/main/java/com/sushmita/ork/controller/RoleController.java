package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.NavPermission;
import com.sushmita.ork.entity.Role;
import com.sushmita.ork.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.sushmita.ork.constants.APIConstants.ROLE_API;

/**
 * @author Sushmita Budhathoki on 2024-09-01
 */


@RestController
@RequestMapping(ROLE_API)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllRole() {
        try {
            Set<Role> roleSet = roleService.getAllRole();
            return CustomResponse.getSuccessResponse("Successfully fetched data", roleSet);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't get roles", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-by-role")
    public ResponseEntity<?> getAllNavPermissionsByRole() {
        try {
            List<NavPermission> roleSet = roleService.getAllNavPermissionsByRole();
            return CustomResponse.getSuccessResponse("Successfully fetched data", roleSet);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse("Couldn't get roles", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
