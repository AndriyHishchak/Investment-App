package com.project.Investment.App.config;

import com.project.Investment.App.commandLineRunner.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@Slf4j
public class AppBeanConfig {

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.user}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Runner runner() {
        return new Runner();
    }

    @Bean
    public Connection connection() {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("Database connection successful");
            return connection;
        } catch (Exception ex) {
            log.info("GetConnection() Error --> {}", ex.getMessage());
            throw new IllegalArgumentException("Incorrect data for the connection from the database");
        }
    }
}
