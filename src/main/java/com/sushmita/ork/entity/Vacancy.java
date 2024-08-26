package com.sushmita.ork.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-08-25
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy extends AbstractPersistable<Long> implements Serializable {

   private String title;
   private String description;
   private String educationQualification;
   private String requirement;
   private Date startDate;
   private Date deadline;
   private Date createdAt;
   private String salary;
}
