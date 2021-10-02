package edu.ifam.projetofic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifam.projetofic.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
