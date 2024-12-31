package com.sushmita.ork.service.orkImage;

import com.sushmita.ork.entity.OrkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-12-31
 */

@Repository
public interface OrkFileRepository extends JpaRepository<OrkFile, Long> {

    OrkFile findByName(String name);
}
