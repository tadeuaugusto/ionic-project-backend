package com.tadeu.estudo.springbootionic.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.domain.Pedido;

@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
