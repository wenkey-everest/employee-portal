package com.everest.employeeportal;

import com.everest.employeeportal.config.DbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DbConfig.class)
public class EmployeeportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeportalApplication.class, args);
	}

}
