package com.tadeu.estudo.springbootionic.dto;

import java.io.Serializable;

import com.tadeu.estudo.springbootionic.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double price;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Produto obj) {
		// TODO Auto-generated constructor stub
		id = obj.getId();
		nome = obj.getNome();
		price = obj.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
