package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MicroserviceBootApplication {

	public static void main(String[] args) {
		 SpringApplication.run(MicroserviceBootApplication.class, args);
	}

}
