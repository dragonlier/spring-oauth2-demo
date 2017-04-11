package org.lhj.demo.auth.controller;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RestController
public class CheckService {
	
	@RequestMapping("/check")
	public String user(Principal user) {
		if(log.isDebugEnabled()) {
			log.debug("user: " + user);
		}
		if(user == null) {
			return null;
		}
		String name = user.getName();
		AuthorityUtils.authorityListToSet(((Authentication)user).getAuthorities());
		/*
		String tokenValue = null;
		if(user instanceof OAuth2Authentication) {
			OAuth2Authentication oauth = (OAuth2Authentication) user;
			OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails)oauth.getDetails();
			tokenValue = detail.getTokenValue();
			System.out.println("Check: " + tokenValue);
			System.out.println("Check: " + detail.getClass());
		}
		*/
		System.out.println("Check: " + user.getClass());
		System.out.println("Check: " + name);
		return name;
	}
}
