package com.sena.crud_basic.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Cliente;
import com.sena.crud_basic.model.Pedido;
import com.sena.crud_basic.model.Pedido.EstadoPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}