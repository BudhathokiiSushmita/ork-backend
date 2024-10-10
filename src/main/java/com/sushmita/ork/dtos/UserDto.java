package com.sushmita.ork.dtos;

import com.sushmita.ork.entity.Role;
import com.sushmita.ork.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Sushmita Budhathoki on 2024-08-28
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Builder
public class UserDto {

    private String username;

    private String emailAddress;

    private String contactNumber;

    private RoleType role;

    private Role actualRole;

}
