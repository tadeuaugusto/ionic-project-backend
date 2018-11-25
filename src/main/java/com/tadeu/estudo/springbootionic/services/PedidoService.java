package com.tadeu.estudo.springbootionic.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.domain.ItemPedido;
import com.tadeu.estudo.springbootionic.domain.PagamentoComBoleto;
import com.tadeu.estudo.springbootionic.domain.Pedido;
import com.tadeu.estudo.springbootionic.domain.enums.EstadoPagamento;
import com.tadeu.estudo.springbootionic.repositories.ItemPedidoRepository;
import com.tadeu.estudo.springbootionic.repositories.PagamentoRepository;
import com.tadeu.estudo.springbootionic.repositories.PedidoRepository;
import com.tadeu.estudo.springbootionic.security.UserSpringScy;
import com.tadeu.estudo.springbootionic.services.exception.AuthorizationException;
import com.tadeu.estudo.springbootionic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido pedido) {
		// TODO Auto-generated method stub
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preenchePagamentoComBoleto(pgto, pedido.getInstante());
		}
		
		pedido = repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPrice());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		
		return pedido;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		
		UserSpringScy user = UserService.usuarioLogado();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
}