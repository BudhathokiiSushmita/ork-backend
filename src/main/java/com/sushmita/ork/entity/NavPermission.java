package com.sushmita.ork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-23
 *
 * This should be populated through sql patch
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NavPermission extends AbstractPersistable<Long> implements Serializable {

    private String name;

    @ManyToMany(mappedBy = "navPermissions") //this means OrkRole is the owning entity. It also ensures that only one bridge is formed.
    private Set<OrkRole> roles;
}
