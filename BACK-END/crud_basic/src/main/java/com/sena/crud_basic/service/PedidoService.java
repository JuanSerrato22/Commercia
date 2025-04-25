package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.crud_basic.model.Cliente;
import com.sena.crud_basic.model.DetallePedido;
import com.sena.crud_basic.model.Pedido;
import com.sena.crud_basic.model.Pedido.EstadoPedido;
import com.sena.crud_basic.model.Producto;
import com.sena.crud_basic.repository.PedidoRepository;
import com.sena.crud_basic.repository.ProductoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }
    
    public List<Pedido> findByCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }
    
    public List<Pedido> findByEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }
    
    public List<Pedido> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return pedidoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        // Verificar stock antes de crear el pedido
        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = detalle.getProducto();
            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }
        
        // Actualizar stock
        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);
        }
        
        return pedidoRepository.save(pedido);
    }
    
    @Transactional
    public Pedido actualizarEstadoPedido(Long id, EstadoPedido nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstado(nuevoEstado);
            
            // Si se cancela el pedido, restaurar stock
            if (nuevoEstado == EstadoPedido.CANCELADO) {
                for (DetallePedido detalle : pedido.getDetalles()) {
                    Producto producto = detalle.getProducto();
                    producto.setStock(producto.getStock() + detalle.getCantidad());
                    productoRepository.save(producto);
                }
            }
            
            return pedidoRepository.save(pedido);
        }
        throw new RuntimeException("Pedido no encontrado con ID: " + id);
    }
    
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    public void save(Pedido existing) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}