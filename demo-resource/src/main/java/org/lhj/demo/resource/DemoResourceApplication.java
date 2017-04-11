package org.lhj.demo.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableResourceServer
//@EnableWebSecurity
public class DemoResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoResourceApplication.class, args);
	}
}
