package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-08-29
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  extends BaseEntity {

    private String username;

    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    private OrkRole orkRole;
    private String email;
    private String contactNumber;

    //for applicant user ONLY
    @OneToMany(fetch = FetchType.EAGER)
    private List<Application> applications;
}

