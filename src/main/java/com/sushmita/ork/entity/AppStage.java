package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import com.sushmita.ork.enums.Action;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppStage extends BaseEntity   {

    private Action action;

    @ManyToOne
    private OrkUser fromUser;

    @ManyToOne
    private OrkUser toUser;
}
