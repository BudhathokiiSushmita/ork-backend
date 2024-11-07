package com.sushmita.ork.service.vacancy;

import com.sushmita.ork.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-11-04
 */

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> getVacanciesByCreatedBy(Long createdBy);
}
