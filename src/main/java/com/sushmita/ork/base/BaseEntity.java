package com.sushmita.ork.base;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-08-26
 */

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @CreatedDate
   @Basic(optional = false)
   @Temporal(TemporalType.TIMESTAMP)
   @Column(columnDefinition = "DATETIME")
   private Date created = new Date();

   @LastModifiedDate
   @Basic
   @Temporal(TemporalType.TIMESTAMP)
   @Column(columnDefinition = "DATETIME")
   private Date lastModified;

   @CreatedBy
   @Column(updatable = false)
   private Long createdBy;

}
