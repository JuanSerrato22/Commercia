package com.sena.crud_basic.controller;

import com.sena.crud_basic.model.ProductoProveedor;
import com.sena.crud_basic.model.ProductoProveedorId;
import com.sena.crud_basic.service.ProductoProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos-proveedores")
public class ProductoProveedorController {

    @Autowired
    private ProductoProveedorService productoProveedorService;

    // Obtener todos
    @GetMapping
    public List<ProductoProveedor> listarTodos() {
        return productoProveedorService.listarTodos();
    }

    // Obtener uno por ID compuesto
    @GetMapping("/{productoId}/{proveedorId}")
    public ResponseEntity<ProductoProveedor> obtener(@PathVariable Long productoId, @PathVariable Long proveedorId) {
        ProductoProveedorId id = new ProductoProveedorId(productoId, proveedorId);
        Optional<ProductoProveedor> productoProveedor = productoProveedorService.obtenerPorId(id);
        return productoProveedor.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear
    @PostMapping
    public ProductoProveedor crear(@RequestBody ProductoProveedor productoProveedor) {
        return productoProveedorService.guardar(productoProveedor);
    }

    // Eliminar
    @DeleteMapping("/{productoId}/{proveedorId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long productoId, @PathVariable Long proveedorId) {
        ProductoProveedorId id = new ProductoProveedorId(productoId, proveedorId);
        productoProveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
