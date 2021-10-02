package edu.ifam.projetofic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifam.projetofic.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
