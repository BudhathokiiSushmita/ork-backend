package com.sushmita.ork.service.applicationInfo;

import com.sushmita.ork.entity.ApplicationUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface ApplicationInfoRepository extends JpaRepository<ApplicationUserInfo, Long> {
}
