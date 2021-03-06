package edu.ifam.projetofic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifam.projetofic.domain.Produto;
import edu.ifam.projetofic.repository.ProdutoRepository;
import edu.ifam.projetofic.service.exception.DataIntegrityException;
import edu.ifam.projetofic.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto listar(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto Nao Encontrado! ID: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Produto inserir(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto atualizar(Integer id, Produto produto) {
		produto.setId(id);
		return produtoRepository.save(produto);
	}
	
	public void excluir(Integer id) {
		listar(id);
		
		try {
			produtoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao foi possivel realizar a exclusao! " 
					+ "ID: " + id + ", Tipo: " + Produto.class.getName());
		}
	}
	
	public List<Produto> listarTodos() {	
		return produtoRepository.findAll();
	}
}
