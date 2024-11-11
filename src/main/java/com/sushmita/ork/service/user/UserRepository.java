package com.sushmita.ork.service.user;

import com.sushmita.ork.entity.OrkUser;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Repository
public interface UserRepository extends JpaRepository<OrkUser, Long>{

    //query method provided by JPA
    Optional<OrkUser> findUserByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);

    List<OrkUser> getAllByCreatedBy(Long id);
}
