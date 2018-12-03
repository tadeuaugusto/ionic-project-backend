package com.tadeu.estudo.springbootionic.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService extends AbstractEmailService {

	// @Autowired
	private MailSender mailSender;

	// @Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		LOG.info("Teste de envio de email em DEV...");
		
		mailSender.send(msg);
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		LOG.info("Teste de envio de email em DEV...");
		
		javaMailSender.send(msg);
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
	}
}
