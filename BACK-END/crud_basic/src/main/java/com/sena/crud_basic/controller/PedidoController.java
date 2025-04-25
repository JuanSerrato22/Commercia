package com.sena.crud_basic.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.Cliente;
import com.sena.crud_basic.model.Pedido;
import com.sena.crud_basic.model.Pedido.EstadoPedido;
import com.sena.crud_basic.service.ClienteService;
import com.sena.crud_basic.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidosByCliente(@PathVariable Long clienteId) {
        return clienteService.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(pedidoService.findByCliente(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estado/{estado}")
    public List<Pedido> getPedidosByEstado(@PathVariable EstadoPedido estado) {
        return pedidoService.findByEstado(estado);
    }
    
    @GetMapping("/fechas")
    public List<Pedido> getPedidosByFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return pedidoService.findByFechaBetween(inicio, fin);
    }
    
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        try {
            // Verificar que el cliente existe
            if (pedido.getCliente() == null || pedido.getCliente().getId() == null ||
                    !clienteService.findById(pedido.getCliente().getId()).isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Asignar el cliente completo
            Cliente cliente = clienteService.findById(pedido.getCliente().getId()).get();
            pedido.setCliente(cliente);
            
            Pedido nuevoPedido = pedidoService.crearPedido(pedido);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoPedido);
        } catch (RuntimeException e) {
            // Error al crear el pedido (posiblemente por stock insuficiente)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> updateEstadoPedido(
            @PathVariable Long id,
            @RequestParam EstadoPedido estado) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, estado);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (!pedidoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}