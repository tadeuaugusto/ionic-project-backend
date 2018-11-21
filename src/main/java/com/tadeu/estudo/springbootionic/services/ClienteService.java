package com.tadeu.estudo.springbootionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.repositories.ClienteRepository;
import com.tadeu.estudo.springbootionic.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}