package com.sushmita.ork.service.application;

import com.sushmita.ork.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByUserId(Long id);
    List<Application> findAllByVacancyCreatedBy(Long id);
}
