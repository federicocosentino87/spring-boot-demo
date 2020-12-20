package com.example.demo;

import com.example.demo.config.SpringProperties;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(SpringProperties.class)
public class DemoApplication {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SpringProperties springProperties;
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Bean
	@Profile("dev")
	CommandLineRunner runner() {
		return args -> {
			System.out.println("This is something i only do in DEV " + springProperties.getWelcomeMsg());
			String[] beans = applicationContext.getBeanDefinitionNames();
			Arrays.sort(beans);
			
//			for (String bean:beans) {
//				log.debug(bean);
//			}
		};
	}

}
