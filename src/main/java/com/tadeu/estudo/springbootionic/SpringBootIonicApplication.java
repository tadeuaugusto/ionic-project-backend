package com.tadeu.estudo.springbootionic;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tadeu.estudo.springbootionic.domain.Categoria;
import com.tadeu.estudo.springbootionic.domain.Cidade;
import com.tadeu.estudo.springbootionic.domain.Cliente;
import com.tadeu.estudo.springbootionic.domain.Endereco;
import com.tadeu.estudo.springbootionic.domain.Estado;
import com.tadeu.estudo.springbootionic.domain.ItemPedido;
import com.tadeu.estudo.springbootionic.domain.Pagamento;
import com.tadeu.estudo.springbootionic.domain.PagamentoComBoleto;
import com.tadeu.estudo.springbootionic.domain.PagamentoComCartao;
import com.tadeu.estudo.springbootionic.domain.Pedido;
import com.tadeu.estudo.springbootionic.domain.Produto;
import com.tadeu.estudo.springbootionic.domain.enums.EstadoPagamento;
import com.tadeu.estudo.springbootionic.domain.enums.TipoCliente;
import com.tadeu.estudo.springbootionic.repositories.CategoriaRepository;
import com.tadeu.estudo.springbootionic.repositories.CidadeRepository;
import com.tadeu.estudo.springbootionic.repositories.ClienteRepository;
import com.tadeu.estudo.springbootionic.repositories.EnderecoRepository;
import com.tadeu.estudo.springbootionic.repositories.EstadoRepository;
import com.tadeu.estudo.springbootionic.repositories.ItemPedidoRepository;
import com.tadeu.estudo.springbootionic.repositories.PagamentoRepository;
import com.tadeu.estudo.springbootionic.repositories.PedidoRepository;
import com.tadeu.estudo.springbootionic.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBootIonicApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Ajja");
		Categoria cat2 = new Categoria(null, "E-clip");
		Categoria cat3 = new Categoria(null, "Astrix");
		Categoria cat4 = new Categoria(null, "1200 Mics");
		Categoria cat5 = new Categoria(null, "Grub");
		Categoria cat6 = new Categoria(null, "Sensient");
		Categoria cat7 = new Categoria(null, "Kromagon");
		Categoria cat8 = new Categoria(null, "Liquid Soul");
		Categoria cat9 = new Categoria(null, "Fungus Funk");
		Categoria cat10 = new Categoria(null, "Drip Drop");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PF);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93088392"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
