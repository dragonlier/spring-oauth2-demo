package org.lhj.demo.auth.controller;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RestController
public class UserService {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		if(log.isDebugEnabled()) {
			log.debug("user: " + user);
		}
		/*
		if(user instanceof OAuth2Authentication) {
			OAuth2Authentication oauth = (OAuth2Authentication) user;
			OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails)oauth.getDetails();
			System.out.println(detail.getTokenValue());
			System.out.println(detail.getClass());
		}
		*/
		
		System.out.println(user.getClass());
		System.out.println(user.getName());
		return user;
	}
	
}
