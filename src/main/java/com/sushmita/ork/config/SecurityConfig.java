package com.sushmita.ork.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ServiceConfigurationError;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 *
 * TYPES: There are many ways to implement Spring Security - SecurityFilterChain, WebSecurityConfigurerAdapter,
 * Method based, @Preauthorize, @RolesAllowed, @Secured. Depends on the project's need, we choose these.
 *
 * METHODS: SecurityFilterChain is latest in Spring 5.0. Even inside this, we have different ways to handle request
 * to endpoint. CSRF is diabled to CSRF token isnot there for when it is not a web browser. authorizeRequests
 * is to handle access using in the deprecated class, but there is authorizeHttpRequests which is new since
 * Spring 5.0. It allows to add custom filter, lambda expression and is used inside SFC.
 *
 * MECHANISM: For the spring security, we have filter -> authentication manager
 * -> authentication providers -> UserDetailsService
 */
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
       try {
           httpSecurity.csrf(c -> c.disable())
                   .authorizeHttpRequests(requests -> requests
                           .requestMatchers("/users/**").permitAll()
                           .anyRequest().authenticated());

           return httpSecurity.build();
       } catch (Exception e) {
           throw new ServiceConfigurationError("Not authenticated to access this");
       }
    }
}
