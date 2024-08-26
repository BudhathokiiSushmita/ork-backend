package com.sushmita.ork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application extends AbstractPersistable<Long> implements Serializable {

    @OneToOne
    private Vacancy vacancy;
    private Date submittedDate;

    @OneToMany
    private Set<AppStage> oldStages;
    @OneToOne
    private AppStage recentStage;

    //these needs to be file
    private String resume;
    private String coverLetter;


}
