package com.tadeu.estudo.springbootionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tadeu.estudo.springbootionic.services.DBService;
import com.tadeu.estudo.springbootionic.services.EmailService;
import com.tadeu.estudo.springbootionic.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class ProfileDevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbStrategy;
	
	@Bean
	public boolean instantiateDB() throws ParseException {
		
		if (!"create".equals(dbStrategy)) {
			return false;
		}
		dbService.instantiateTestDB();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
