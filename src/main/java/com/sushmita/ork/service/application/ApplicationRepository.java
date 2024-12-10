package com.sushmita.ork.service.application;

import com.sushmita.ork.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
