package com.conversor;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConversorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversorApplication.class, args);
	}
	
	@Bean
	public GroupedOpenApi productApi2() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.packagesToScan("com.conversor")
				.build();
	}
	
}
