package com.sushmita.ork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

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
 * - Will add area, admin can add this
 *
 * Recruiter
 * - Signup based on company
 * - Login
 * - Posts vacancy based on area
 * - Forwards to HR of the company
 * - will create HR and Director user
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/companies/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/companies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/sector/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/vacancy/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.POST, "/users/application/create").hasAuthority("APPLICANT_WRITE")
                        .requestMatchers(HttpMethod.POST, "/users/application/stages").hasAnyAuthority("RECRUITER", "HR", "DIRECTOR")
                        .requestMatchers("/users/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
