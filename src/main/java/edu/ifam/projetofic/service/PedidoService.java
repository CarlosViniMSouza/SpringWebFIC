package edu.ifam.projetofic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Pedido;
import edu.ifam.projetofic.repository.ItemPedidoRepository;
import edu.ifam.projetofic.repository.PedidoRepository;
import edu.ifam.projetofic.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public Pedido listar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido Nao Encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido inserir(Pedido pedido) {
		Pedido pedidoSaved = pedidoRepository.save(pedido);
		
		pedido.getItensPedido().forEach(itemPedido -> {
			itemPedido.setPedido(pedidoSaved);
		});
		
		itemPedidoRepository.saveAll(pedido.getItensPedido());
		
		return pedidoRepository.save(pedido);
	}
	
	public Pedido atualizar(Pedido pedido, Integer id) {
		pedido.setId(id);
		
		pedido.getItensPedido().forEach(itemPedido -> {
			itemPedido.setPedido(pedido);
		});
		
		itemPedidoRepository.saveAll(pedido.getItensPedido());
		
		return pedidoRepository.save(pedido);
	}
	
	public void excluir(Integer id) {
		Pedido pedido = listar(id);
		
		List<Integer> ids = new ArrayList<>();
		
		pedido.getItensPedido().forEach(itemPedidoRepository -> {
			ids.add(itemPedidoRepository.getId());
		});
		
		itemPedidoRepository.deleteAllById(ids);
	}
	
	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
	}
}
