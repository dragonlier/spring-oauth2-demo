package org.lhj.demo.resource.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Configuration
public class ResourceController {
	
	@RequestMapping("/resource")
	@CrossOrigin(origins="*", maxAge=3600)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		if(log.isDebugEnabled()) {
			log.debug("/resource: " + model);
		}
		return model;
	}
	
	@RequestMapping("/")
	@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with"})
	public Map<String, Object> resource() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		if(log.isDebugEnabled()) {
			log.debug("/resource: " + model);
		}
		return model;
	}
}
