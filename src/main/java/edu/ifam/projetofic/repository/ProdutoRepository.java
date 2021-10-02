package edu.ifam.projetofic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifam.projetofic.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
