package edu.ifam.projetofic.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.ifam.projetofic.domain.Cliente;
import edu.ifam.projetofic.service.ClienteService;

public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Cliente cliente = clienteService.listar(id);
		
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> inserir(@RequestBody Cliente cliente) {
		cliente = clienteService.inserir(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		cliente = clienteService.atualizar(id, cliente);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Integer id) {
		clienteService.excluir(id);
		
		return ResponseEntity.noContent().build();	
	}
}