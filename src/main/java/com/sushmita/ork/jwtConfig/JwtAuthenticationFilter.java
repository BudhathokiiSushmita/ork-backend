package com.sushmita.ork.jwtConfig;

import com.sushmita.ork.service.user.OrkUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Sushmita Budhathoki on 2024-08-30
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private OrkUserDetailService orkUserDetailService;


    //link in SFC, before getting to controller, checks if there is token in the header
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //getting token from request header
        String bearerToken = request.getHeader("Authorization");
        String token = null;
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7, bearerToken.length());
        }

        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            //getting username from token
            String username = jwtGenerator.getUsernameFromJwt(token);

            //getting UserDetails using username
            UserDetails userDetails = orkUserDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                System.out.println("Authentication in SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication().getName());
            }
        }

        try {
            System.out.println("Before filterChain.doFilter");
            filterChain.doFilter(request, response);
            System.out.println("After filterChain.doFilter");
        } catch (Exception e) {
            System.out.println("Error in filter chain: " + e.getMessage());
            throw e;
        }
    }
}
