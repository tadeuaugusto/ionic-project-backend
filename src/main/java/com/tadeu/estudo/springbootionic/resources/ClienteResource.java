package com.tadeu.estudo.springbootionic.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.domain.enums.TipoCliente;
import com.tadeu.estudo.springbootionic.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Cliente> listar() {
		Cliente c1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PF);
		Cliente c2 = new Cliente(null, "Tadeu", "tadeu@gmail.com", "00000000000191", TipoCliente.PJ);
		
		List<Cliente> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(c1, c2));
		
		return lista;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
