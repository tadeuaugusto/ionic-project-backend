package com.tadeu.estudo.springbootionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.tadeu.estudo.springbootionic.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
