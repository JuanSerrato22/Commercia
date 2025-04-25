package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.DetallePedido;
import com.sena.crud_basic.model.Pedido;
import com.sena.crud_basic.model.Producto;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido(Pedido pedido);
    List<DetallePedido> findByProducto(Producto producto);
}