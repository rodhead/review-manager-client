package org.block.analytics;

import org.block.analytics.core.utility.ApllicationPropertiesValueBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("org.block.analytics.*")
@EnableConfigurationProperties(ApllicationPropertiesValueBinder.class)
@EnableMongoRepositories
public class ReviewManagerClientApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReviewManagerClientApplication.class, args);
	}
}
