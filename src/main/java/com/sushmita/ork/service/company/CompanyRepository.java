package com.sushmita.ork.service.company;

import com.sushmita.ork.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sushmita Budhathoki on 2024-10-22
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByCreatedBy(long currentUserId);
}
