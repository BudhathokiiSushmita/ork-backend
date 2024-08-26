package com.sushmita.ork.base;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sushmita Budhathoki on 2024-08-26
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @CreatedDate
   @Basic(optional = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date created = new Date();

   @LastModifiedDate
   @Basic
   @Temporal(TemporalType.TIMESTAMP)
   private Date lastModified;

   @CreatedBy
   @Column(updatable = false)
   private Long createdBy;
}
