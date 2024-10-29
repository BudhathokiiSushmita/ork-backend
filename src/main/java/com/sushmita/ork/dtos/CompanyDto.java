package com.sushmita.ork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-10-28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String address;
    private UserResponseDto createdBy;
    private Date created;

}
