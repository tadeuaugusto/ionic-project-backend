package com.tadeu.estudo.springbootionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tadeu.estudo.springbootionic.services.DBService;

@Configuration
@Profile("test")
public class ProfileTestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDB() throws ParseException {
		dbService.instantiateTestDB();
		return true;
	}
}
