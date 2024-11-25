package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class Application extends BaseEntity   {

    @OneToOne
    private Vacancy vacancy;
    private Date submittedDate;

    @OneToMany
    private Set<AppStage> oldStages;
    @OneToOne
    private AppStage recentStage;

    //these needs to be as file
    private String resume;
    private String coverLetter;

}
