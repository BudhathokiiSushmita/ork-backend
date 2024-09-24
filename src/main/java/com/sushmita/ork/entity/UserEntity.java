package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-08-29
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity   extends BaseEntity {

    private String username;

    private String password;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private OrkRole orkRole;
    private String email;
    private String contactNumber;

    //for applicant user ONLY
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Application> applications;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id"),
    inverseJoinColumns =  @JoinColumn(name= "role_id", referencedColumnName = "id"))
    private List<Roles> roles = new ArrayList<>();
}

