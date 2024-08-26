package com.sushmita.ork.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Set;

/**
 * @author Sushmita Budhathoki on 2024-08-26
 *
 * PostConstruct executes method right before initializing the app
 */

@Component
public class SqlPatch {

    @Autowired
    private DataSource dataSource; //for db connectivity

    @Autowired
    private ResourceLoader resourceLoader;

    private final String BASE_URL = "classpath:db/patch/";

    @PostConstruct
    public void executePatches() {
        System.out.println("patching data");
        Set<String> sqlFiles = Set.of(
                "nav_permission.sql",
                "ork_role.sql",
                "role_nav_permission.sql"
        );
        sqlFiles.stream().forEach(f -> {
            Resource resource = resourceLoader.getResource(BASE_URL + f); //to read file from classpath or any other location
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource); //Spring tool, executes SQL script,
            // legacy buffer reader could be used but this is easy way
            populator.execute(dataSource);
        });

        System.out.println("all patched. good to go");
    }


}
