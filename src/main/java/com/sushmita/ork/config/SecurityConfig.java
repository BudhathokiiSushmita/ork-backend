package com.sushmita.ork.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ServiceConfigurationError;

import static com.sushmita.ork.enums.ActionPermission.APPLICANT_WRITE;
import static com.sushmita.ork.enums.Role.*;

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
 *
 *
 * Admin
 * - Will Add companies
 * - Will add area
 *
 * Recruiter
 * - Signup based on company
 * - Login
 * - Posts vacancy based on area
 * - Forwards to HR of the company
 *
 * HR
 * - Verifies if the job seeker is eligible (just by looking at)
 * -  Forwards to Director of the company
 *
 * Director
 * - Approves and sets time for interview
 *
 * Applicant
 * - Signup
 * - Login
 * - Sees multiple companies before login, this is PUBLIC
 * - Can apply to the hiring post
 * - Sees the process throughout
 *
 * Same login page, dashboard and navigation is based on role's nav permissions
 */
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
       try {
           httpSecurity.csrf(c -> c.disable())
                   .authorizeHttpRequests(requests -> requests
                           .requestMatchers(HttpMethod.GET,"/companies/**").permitAll() //PUBLIC
                           .requestMatchers(HttpMethod.POST,"/companies/**").hasRole(ADMIN.name())

                           .requestMatchers(HttpMethod.POST,"/sector/**").hasRole(ADMIN.name())
                           .requestMatchers(HttpMethod.POST,"/vacancy/**").hasRole(RECRUITER.name())

                           .requestMatchers(HttpMethod.POST, "/users/application/create").hasAuthority(APPLICANT_WRITE.name())
                           .requestMatchers(HttpMethod.POST, "/users/application/stages").hasAnyAuthority(RECRUITER.name(), HR.name(), DIRECTOR.name())
                           .anyRequest().authenticated());
           return httpSecurity.build();
       } catch (Exception e) {
           throw new ServiceConfigurationError("Not authenticated to access this");
       }
    }
}
