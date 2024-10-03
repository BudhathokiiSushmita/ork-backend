package com.sushmita.ork.service.sector;

import com.sushmita.ork.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-10-02
 */

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
