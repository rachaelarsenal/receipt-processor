package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
public class ReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptProcessorApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer
	propertySourcesPlaceholderConfigurer()
		throws Exception {

		PropertySourcesPlaceholderConfigurer
			propertySourcesPlaceholderConfigurer =
			new PropertySourcesPlaceholderConfigurer();

		PathMatchingResourcePatternResolver
			pathMatchingResourcePatternResolver =
			new PathMatchingResourcePatternResolver();

		propertySourcesPlaceholderConfigurer.setLocations(
			pathMatchingResourcePatternResolver.getResources(
				"classpath*:/application.properties"));

		return propertySourcesPlaceholderConfigurer;
	}

}