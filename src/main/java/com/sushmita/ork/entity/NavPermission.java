package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class NavPermission extends BaseEntity   {

    private String name;

    private Long view_order;
}
