package edu.ifam.projetofic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Pedido;
import edu.ifam.projetofic.repository.PedidoRepository;
import edu.ifam.projetofic.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido listar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido Nao Encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido inserir(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido atualizar(Integer id, Pedido pedido) {
		pedido.setId(id);
		return pedidoRepository.save(pedido);
	}
	
	public void excluir(Integer id) {
		pedidoRepository.deleteById(id);
	}
	
	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
	}
}
