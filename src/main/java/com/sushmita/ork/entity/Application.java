package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.StageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application extends BaseEntity   {

    @ManyToOne
    private Vacancy vacancy;
    private Date submittedDate;

    @OneToMany
    private Set<AppStage> oldStages;

    @OneToOne
    private AppStage recentStage;

    //these needs to be as file (docs)
    private String resume;
    private String coverLetter;

    //questionnaire
    @Column(columnDefinition = "LONGTEXT")
    private String professionChoice;

    @Column(columnDefinition = "LONGTEXT")
    private String companyChoice;

    @Column(columnDefinition = "LONGTEXT")
    private String uniqueQualities;

    private Long userId;

    private StageStatus stageStatus;

    @ManyToOne
    private ApplicationUserInfo applicationUserInfoId;
}
