package com.tadeu.estudo.springbootionic.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.repositories.ClienteRepository;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
	}
	
	public String newPassword() {
		char[] vetor = new char[10];
		for (int i = 0; i < 10; i++) {
			vetor[i] = randomChar();
		}
		return new String(vetor);
	}
	
	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
