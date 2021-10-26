package edu.ifam.projetofic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Categoria;
import edu.ifam.projetofic.repository.CategoriaRepository;
import edu.ifam.projetofic.service.exception.DataIntegrityException;
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
		listar(id);
		
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao foi possivel realizar a exclusao! " 
					+ "ID: " + id + ", Tipo: " + Categoria.class.getName());
		}
		
	}
	
	public List<Categoria> listarTodos() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> listarPagina(
			Integer page,
			Integer size,
			String ord,
			String dir) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir), ord);
		return categoriaRepository.findAll(pageRequest);
	}
}
