package com.sushmita.ork.dtos;

import com.sushmita.ork.entity.Company;
import com.sushmita.ork.entity.Sector;
import com.sushmita.ork.enums.VacancyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2026-09-03
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {

        private Long id;
        private String title;
        private VacancyType vacancyType;
        private Long positionNumber;
        private String description;
        private String qualification;
        private String requirement;
        private Date startDate;
        private Date deadline;
        private Date createdAt;
        private String salaryRange;
        private String applicationProcedure;
        private String documentRequirement;
        private Boolean isPaidPosition;

        private Company company;
        private Sector sector;

        private Boolean isApplied;
}
