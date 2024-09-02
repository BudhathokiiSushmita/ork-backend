package com.sushmita.ork.config;

import com.sushmita.ork.jwtConfig.JwtAuthException;
import com.sushmita.ork.jwtConfig.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Sushmita Budhathoki on 2024-08-21
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthException jwtAuthException;

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

                        //user
                        .requestMatchers(HttpMethod.POST,"/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()

                        //role
                        .requestMatchers(HttpMethod.GET, "/roles/all").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e ->
                        e.authenticationEntryPoint(jwtAuthException))
                //need to disable session creation
                .sessionManagement(s ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //adding filter
        http.addFilterBefore(
                new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
