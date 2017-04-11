package org.lhj.demo.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@SpringBootApplication
//@EnableZuulProxy
//@EnableOAuth2Sso
//@EnableWebSecurity
@EnableAutoConfiguration
public class DemoUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoUiApplication.class, args);
	}
	
	@Bean
	protected OAuth2RestTemplate oauth2RestTemplate(
	    OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
	  return new OAuth2RestTemplate(resource, context);
	}
}
