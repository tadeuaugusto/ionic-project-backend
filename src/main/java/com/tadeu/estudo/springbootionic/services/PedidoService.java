package com.tadeu.estudo.springbootionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.domain.Pedido;
import com.tadeu.estudo.springbootionic.repositories.PedidoRepository;
import com.tadeu.estudo.springbootionic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}