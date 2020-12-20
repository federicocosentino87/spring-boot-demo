package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.config.SpringProperties;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Link;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LinkRepository;

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
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
	    return args -> {
	        Link link = new Link("Getting Started with Spring Boot 2","https://therealdanvega.com/spring-boot-2");
	        linkRepository.save( link );

	        Comment comment = new Comment("This Spring Boot 2 Link is awesome",link);
	        commentRepository.save(comment);
	        link.addComment(comment);
	        
	        Link firstLink = linkRepository.findByTitle("Getting Started with Spring Boot 2");

	        System.out.println(firstLink.getTitle());
	    };
	}

//	@Bean
//	@Profile("dev")
//	CommandLineRunner runner() {
//		return args -> {
//			System.out.println("This is something i only do in DEV " + springProperties.getWelcomeMsg());
//			String[] beans = applicationContext.getBeanDefinitionNames();
//			Arrays.sort(beans);
//			
//			for (String bean:beans) {
//				log.debug(bean);
//			}
//		};
//	}

}
