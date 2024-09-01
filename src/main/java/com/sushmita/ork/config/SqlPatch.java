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
    public void executePatches() throws InterruptedException {
        System.out.println("patching data");
        Set<String> sqlFiles = Set.of(
                "nav_permission.sql",
                "ork_role.sql"
        );
        patchData(sqlFiles);

        //As it has foreign keys, so need to wait a lil for executing this
        Thread.sleep(2000);
        patchData(Set.of("role_nav_permission.sql"));
        System.out.println("all patched. good to go");
    }

    private void patchData(Set<String> sqlFiles) {
        sqlFiles.stream().forEach(f -> {
            Resource resource = resourceLoader.getResource(BASE_URL + f); //to read file from classpath or any other location
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource); //Spring tool, executes SQL script,
            // legacy buffer reader could be used but this is easy way
            populator.execute(dataSource);
        });
    }
}
