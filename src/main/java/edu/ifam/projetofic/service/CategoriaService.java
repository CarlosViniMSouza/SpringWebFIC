package edu.ifam.projetofic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Categoria;
import edu.ifam.projetofic.repository.CategoriaRepository;
import edu.ifam.projetofic.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria listar(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Nao Encontrado!! ID: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Integer id, Categoria categoria) {
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}
	
	public void excluir(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
}
