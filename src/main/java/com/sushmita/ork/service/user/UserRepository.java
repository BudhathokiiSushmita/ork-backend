package com.sushmita.ork.service.user;

import com.sushmita.ork.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Sushmita Budhathoki on 2024-08-21
 *
 * [JPA]: It provides a framework for object-relational mapping (ORM) and data persistence,
 * allowing developers to work with databases using Java objects.
 *
 * Advantages:
 * 1. Object-Relational Mapping (ORM): JPA maps Java objects to database
 * tables, allowing you to interact with the database using Java objects rather than SQL queries.
 *
 * 2. Criteria API: JPA includes a Criteria API for building type-safe queries programmatically.
 *
 * 3. First and Second Level Caching
 *
 * 4. Allowing you to manage transactions declaratively using annotations like @Transactional.
 * This simplifies transaction management and ensures consistency.
 *
 * 5. JPA provides annotations for defining and managing relationships between entities,
 * such as @OneToOne, @OneToMany, @ManyToOne, and @ManyToMany
 *
 * 6. JPA is a standard specification in Java, which means you can use different JPA implementations
 * (like Hibernate, EclipseLink, and OpenJPA) interchangeably
 *
 * [Hibernate]: It is a default JPA implementation. It is a ORM.
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);
}
