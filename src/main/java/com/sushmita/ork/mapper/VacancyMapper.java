package com.sushmita.ork.mapper;

import com.sushmita.ork.dtos.VacancyDto;
import com.sushmita.ork.entity.Vacancy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2026-09-03
 */

@Mapper(componentModel = "spring")
public interface VacancyMapper {
   VacancyDto mapEntityToDto(Vacancy vacancy);
   List<VacancyDto> mapEntitiesToDtos(List<Vacancy> vacancyList);
}
