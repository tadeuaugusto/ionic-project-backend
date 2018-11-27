package com.tadeu.estudo.springbootionic.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.tadeu.estudo.springbootionic.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		// TODO Auto-generated method stub
		SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(smm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(pedido.getCliente().getEmail());
		smm.setFrom(sender);
		smm.setSubject("Pedido de confirmado! Código: " + pedido.getId());
		smm.setSentDate(new Date(System.currentTimeMillis()));
		smm.setText(pedido.toString());
		return smm;
	}
}
