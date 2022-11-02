package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

// annotation @Configuration tells that this class is an configuration class for test profile
// informed by @Profile annotation
 
// this class implements CommandLineRunner used to indicate that a bean should run 
// when it is contained within a SpringApplication. 

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	// create UserRepository class dependency injection
	// Spring Boot automatically does that with annotation @Autowired	
	@Autowired
	private UserRepository userRepository;

	//  
	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// Bulk save os Users objects
		userRepository.saveAll( Arrays.asList(u1,u2) );
		
	}

}
