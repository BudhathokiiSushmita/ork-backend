package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-11-25
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EducationQualification extends BaseEntity {

    private String institution;
    private String courseName;
    private String gradeOrPercentage;

    @Column(columnDefinition = "DATETIME")
    private Date startDate;

    @Column(columnDefinition = "DATETIME")
    private Date endDate;
}
