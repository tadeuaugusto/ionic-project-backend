package com.tadeu.estudo.springbootionic.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tadeu.estudo.springbootionic.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context ctx = new Context();
		ctx.setVariable("pedido", pedido);
		
		return templateEngine.process("email/confirmacaoPedido", ctx);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try {
			
			MimeMessage mime = prepareMimeMailMessageFromPedido(pedido);
			sendHtmlEmail(mime);	
		} catch (MessagingException e) {
			// TODO: handle exception
			sendOrderConfirmationEmail(pedido);
		}
	}
	
	protected MimeMessage prepareMimeMailMessageFromPedido(Pedido pedido) throws MessagingException {

		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mime, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido de confirmado! Código: " + pedido.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido));
		return mime;
	}
	
}
