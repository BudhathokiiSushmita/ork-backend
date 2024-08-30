package com.sushmita.ork.dtos;

import lombok.AllArgsConstructor;
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
public class UserDto {

    private String username;

}
