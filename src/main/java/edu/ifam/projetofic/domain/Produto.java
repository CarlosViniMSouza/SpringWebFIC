package edu.ifam.projetofic.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private Float preco;
	
	public Produto() {
		//deixa quieto, vai ser importante esse metodo vazio.
	}
	
	public Produto(Integer id, String description, Float preco) {
		this.id = id;
		this.description = description;
		this.preco = preco;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public Float getPreco() {
		return preco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id) && Objects.equals(preco, other.preco);
	}

}