package com.sushmita.ork.config;

import com.sushmita.ork.jwtConfig.JwtAuthException;
import com.sushmita.ork.jwtConfig.JwtAuthenticationFilter;
import com.sushmita.ork.service.user.OrkUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    private JwtAuthException jwtAuthException;

    private OrkUserDetailService orkUserDetailService;

    @Autowired
    public SecurityConfig(JwtAuthException jwtAuthException, OrkUserDetailService orkUserDetailService) {
        this.jwtAuthException = jwtAuthException;
        this.orkUserDetailService = orkUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //need to configure it later
                .exceptionHandling(e ->
                        e.authenticationEntryPoint(jwtAuthException))
                //need to disable session creation as we are adding jwt
                .sessionManagement(s ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/companies/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/companies/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/sector/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/vacancy/**").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.POST, "/users/application/create").hasAuthority("APPLICANT_WRITE")
                        .requestMatchers(HttpMethod.POST, "/users/application/stages").hasAnyAuthority("RECRUITER", "HR", "DIRECTOR")

                        //user
                        .requestMatchers(HttpMethod.POST,"/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()

                        //role
////                        .requestMatchers(HttpMethod.GET, "/roles/all").permitAll()
//
//                        //nav-permission, later needs to be authenticated
//                        .requestMatchers(HttpMethod.GET, "/nav-permissions/all").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/company/all").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                );

        //adding filter
        http.addFilterBefore(
                jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
