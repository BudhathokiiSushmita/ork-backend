package com.sushmita.ork.service.application;

import com.sushmita.ork.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sushmita Budhathoki on 2024-12-10
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByUserId(Long id);

    List<Application> findAllByVacancyCreatedBy(Long id);

    List<Application> findAllByRecentStage_ToUser_Id(Long id);

    @Query("""
                SELECT a.vacancy.id
                FROM Application a
                WHERE a.userId = :userId
            """)
    List<Long> findVacancyIdsByUserId(@Param("userId") Optional<Long> userId);

    @Query("""
        SELECT DISTINCT a.userId
        FROM Application a
        JOIN Vacancy v on v.id = a.vacancy.id
        where v.createdBy = :userId
        """)
    List<Long> getAllApplicantForRecruiterVacancies(@Param("userId") Optional<Long> userId);



}
