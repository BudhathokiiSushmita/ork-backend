package com.sushmita.ork.service.user;

import com.sushmita.ork.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);
}
