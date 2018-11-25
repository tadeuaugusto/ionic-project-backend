package com.tadeu.estudo.springbootionic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
