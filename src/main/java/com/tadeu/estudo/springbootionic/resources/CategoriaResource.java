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

import com.tadeu.estudo.springbootionic.domain.Categoria;
import com.tadeu.estudo.springbootionic.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(1, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(c1, c2));
		
		return lista;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
