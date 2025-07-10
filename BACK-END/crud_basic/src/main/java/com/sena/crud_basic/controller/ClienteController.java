package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.Cliente;
import com.sena.crud_basic.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        // Verificar si ya existe un cliente con ese email
        if (clienteService.findByEmail(cliente.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Cliente nuevoCliente = clienteService.save(cliente);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCliente);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(id);
                    return ResponseEntity.ok(clienteService.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/inactivar/{id}")
    public ResponseEntity<Object> inactivarCliente(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setActivo(false); // Marcamos el cliente como inactivo
                    clienteService.save(clienteExistente); // Guardamos los cambios
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}