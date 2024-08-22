package com.sushmita.ork.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 *
 * [AbstractPersistable]: is a base class provided by Spring Data JPA that helps simplify
 * the creation of entity classes. It implements the Persistable interface,
 * which defines methods for dealing with entity persistence.
 * It provides method like isNew(), getId().
 * You can use AbstractPersistable when you want to reduce boilerplate code in
 * your entity classes, especially when your entities use Long as the ID type
 * and you don't need any custom ID management logic.
 *
 * [Serializable]: We implement Serializable to convert JPA entity to byte stream.
 * You can certainly persist data to a database without using Serializable. However,
 * if your use case involves serialization (like writing objects to files, sending
 * them over a network, or storing them in sessions for replication), you will need Serializable.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends AbstractPersistable<Long> implements Serializable {

    private String username;
    private String password;
    private String role;
}
