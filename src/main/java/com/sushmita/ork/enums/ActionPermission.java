package com.sushmita.ork.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-08-23
 *
 * These are the actions that can be performed by roles.
 */

@AllArgsConstructor
public enum ActionPermission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),


    RECRUITER_READ("recruiter:read"),
    RECRUITER_WRITE("recruiter:write"),
    RECRUITER_UPDATE("recruiter:update"),
    RECRUITER_DELETE("recruiter:delete"),

    HR_READ("hr:read"),
    HR_WRITE("hr:write"),
    HR_UPDATE("hr:update"),
    HR_DELETE("hr:delete"),

    DIRECTOR_READ("director:read"),
    DIRECTOR_WRITE("director:write"),
    DIRECTOR_UPDATE("director:update"),
    DIRECTOR_DELETE("director:delete"),

    APPLICANT_READ("applicant:read"),
    APPLICANT_WRITE("applicant:write"),
    APPLICANT_UPDATE("applicant:update"),
    APPLICANT_DELETE("applicant:delete");

    @Getter
    private final String actionPermission;
}
