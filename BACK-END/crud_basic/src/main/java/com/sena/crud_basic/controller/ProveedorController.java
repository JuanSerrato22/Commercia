package com.sena.crud_basic.controller;

import com.sena.crud_basic.model.Proveedor;
import com.sena.crud_basic.service.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "http://127.0.0.1:5501") // Ajusta si tu frontend está en otro puerto
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Obtener todos los proveedores
    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarTodos();
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Crear nuevo proveedor
    @PostMapping
    public ResponseEntity<?> crearProveedor(@Valid @RequestBody Proveedor proveedor) {
    // Si el proveedor es válido, se guarda
    return ResponseEntity.ok(proveedorService.guardar(proveedor));
}

    // Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetalles) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor != null) {
            proveedor.setNit(proveedorDetalles.getNit()); // Asegurarte de que se actualiza también el NIT
            proveedor.setNombre(proveedorDetalles.getNombre());
            proveedor.setTelefono(proveedorDetalles.getTelefono());
            proveedor.setEmail(proveedorDetalles.getEmail());
            proveedor.setDireccion(proveedorDetalles.getDireccion());
            return ResponseEntity.ok(proveedorService.guardar(proveedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor != null) {
            proveedorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
