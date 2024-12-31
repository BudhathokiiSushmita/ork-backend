package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-10-02
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sector extends BaseEntity {

    //one sector will have multiple jobs but one job will be associated with only one sector
    private String name;

    @OneToOne
    private OrkFile orkFile;

    @Transient
    private byte[] file;
}
