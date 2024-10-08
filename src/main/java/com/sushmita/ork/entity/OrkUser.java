package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-08-29
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrkUser extends BaseEntity {

    private String username;

    private String password;

    private String email;

    private String contactNumber;

    //for applicant user ONLY
    @OneToMany(fetch = FetchType.EAGER)
    private List<Application> applications;


    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}

