package com.example.cafespringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CafeDbInitializer {
    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Autowired
    public CafeDbInitializer(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void initializeDatabase() {
        executeSqlFromResource("classpath:schema.sql");
    }

    private void executeSqlFromResource(String resourceLocation) {
        try {
            Resource resource = resourceLoader.getResource(resourceLocation);
            String sql = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL from resource: " + resourceLocation, e);
        }
    }
}
