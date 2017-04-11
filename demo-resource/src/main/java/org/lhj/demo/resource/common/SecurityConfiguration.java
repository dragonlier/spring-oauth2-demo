package org.lhj.demo.resource.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Security Configuration for Resource Server.
 * 
 * @author lhj
 *
 */
//@Configuration
//@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("HTTP Security configuration.");
		}
		http.httpBasic().disable();
		http.authorizeRequests().anyRequest().authenticated();
	}
	
}
