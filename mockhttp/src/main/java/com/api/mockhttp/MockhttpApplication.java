package com.api.mockhttp;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;

//@Configuration - configures this class to be source of beans
//@EnableAutoConfiguration - enables the tomcat server, json serialization 
//@ComponentScan - detects @service, @RestController etc components in package
// OR

@SpringBootApplication // just use this one annotation instead of 3 above.
public class MockhttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockhttpApplication.class, args);
	}

}
