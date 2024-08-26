package com.sushmita.ork.entity;

import com.sushmita.ork.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
 * CONS: This generates _seq table, to stop that we need to put primary key as IDENTITY
 * but it is Sequenece by default. Cannot change it, so it is ok to use it if we are not
 * creating table using hibernate. Can use Liquibase instead.
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
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;

    @OneToOne
    private OrkRole orkRole;
    private String email;
    private String contactNumber;

    //for applicant user ONLY
    @OneToMany
    private List<Application> applications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Spring will use list of authorities of each user
        return this.orkRole.getRoleName().getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
