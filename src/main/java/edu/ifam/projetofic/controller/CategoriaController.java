package edu.ifam.projetofic.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.ifam.projetofic.domain.Categoria;
import edu.ifam.projetofic.service.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Categoria categoria = categoriaService.listar(id);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> inserir(@RequestBody Categoria categoria) {
		categoria = categoriaService.inserir(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
		categoria = categoriaService.atualizar(id, categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Integer id) {
		categoriaService.excluir(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listarTodos() {
		List<Categoria> categorias = categoriaService.listarTodos();
		
		return ResponseEntity.ok().body(categorias);
	}
	
	@RequestMapping(value="/pages", method = RequestMethod.GET)
	public ResponseEntity<?> listarPagina(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="2") Integer size,
			@RequestParam(value="ord", defaultValue="description") String ord,
			@RequestParam(value="dir", defaultValue="ASC") String dir
			) {
		Page<Categoria> categorias = categoriaService.listarPagina(page, size, ord, dir);
		
		return ResponseEntity.ok().body(categorias);
	}
}
