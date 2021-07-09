package com.project.Investment.App.config;

import com.project.Investment.App.commandLineRunner.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@Slf4j
public class AppBeanConfig {

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
        String URL = "jdbc:h2:mem:investment";
        String USERNAME = "sa";
        String PASSWORD = "";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        try {
            Connection  connection = DriverManager.getConnection( URL, USERNAME, PASSWORD);
            log.info("Connect");
            return connection;
        } catch (Exception ex) {
            log.info("EntityRepository.getConnection() Error --> {}", ex.getMessage());
            return null;
        }
    }
}
