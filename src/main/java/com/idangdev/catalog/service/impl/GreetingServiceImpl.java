package com.idangdev.catalog.service.impl;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.idangdev.catalog.config.ApplicationProperties;
import com.idangdev.catalog.config.CloudProperties;
import com.idangdev.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);
	
	private ApplicationProperties applicationProperties;
	
	private CloudProperties cloudProperties;
	
	public GreetingServiceImpl(ApplicationProperties applicationProperties, CloudProperties cloudProperties) {
		super();
		this.applicationProperties = applicationProperties;
		this.cloudProperties = cloudProperties;
	}

	@Override
	public String sayGreeting() {
		log.trace("this is log TRACE");
		log.debug("this is log DEBUG");
		log.info("this is log INFO");
		log.warn("this is log WARN");
		log.error("this is log ERROR");
		System.out.println(cloudProperties.getApiKey());
		TimeZone timezone = TimeZone.getTimeZone(applicationProperties.getTimezone());
		return applicationProperties.getWelcomeText()+", our timezone : " + timezone.getDisplayName() + 
				", our currency : " + applicationProperties.getCurrency();
	}

}
