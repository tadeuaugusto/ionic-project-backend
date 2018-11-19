package com.tadeu.estudo.springbootionic.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tadeu.estudo.springbootionic.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(1, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(c1, c2));
		
		return lista;
	}
}
