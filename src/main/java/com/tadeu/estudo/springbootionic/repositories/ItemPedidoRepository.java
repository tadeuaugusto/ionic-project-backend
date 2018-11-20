package com.tadeu.estudo.springbootionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tadeu.estudo.springbootionic.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
