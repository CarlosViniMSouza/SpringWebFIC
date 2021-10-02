package edu.ifam.projetofic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Cliente;
import edu.ifam.projetofic.repository.ClienteRepository;
import edu.ifam.projetofic.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente listar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente Nao Encontrado!! ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente inserir(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(Integer id, Cliente cliente) {
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Integer id) {
		clienteRepository.deleteById(id);
	}
}
