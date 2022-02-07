package com.everest.employeeportal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
@Configuration
@Setter
@Getter
@ConfigurationProperties(value = "spring.datasource")
public class DbConfig {
    private String url;
    private String username;
    private String password;

    private DataSource dataSource(){
        return new DriverManagerDataSource(url, username, password);
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
