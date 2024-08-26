package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.StageStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppStage extends BaseEntity   {

    private StageStatus stageStatus;
    private Date createdAt;
    private Long createdBy;
}
