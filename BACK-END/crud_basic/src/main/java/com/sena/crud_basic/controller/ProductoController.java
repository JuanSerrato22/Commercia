package com.sena.crud_basic.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.Categoria;
import com.sena.crud_basic.model.Producto;
import com.sena.crud_basic.service.CategoriaService;
import com.sena.crud_basic.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Long categoriaId) {
        return categoriaService.findById(categoriaId)
                .map(categoria -> ResponseEntity.ok(productoService.findByCategoria(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar")
    public List<Producto> getProductosByNombre(@RequestParam String nombre) {
        return productoService.findByNombreContaining(nombre);
    }
    
    @GetMapping("/precio")
    public List<Producto> getProductosByPrecio(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return productoService.findByPrecioBetween(min, max);
    }
    
    @GetMapping("/stock-bajo")
    public List<Producto> getProductosStockBajo(@RequestParam(defaultValue = "5") int minimo) {
        return productoService.findByStockBajo(minimo);
    }
    
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        // Verificar que la categoría existe
        if (producto.getCategoria() == null || producto.getCategoria().getId() == null ||
                !categoriaService.findById(producto.getCategoria().getId()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        // Asignar la categoría completa
        Categoria categoria = categoriaService.findById(producto.getCategoria().getId()).get();
        producto.setCategoria(categoria);
        
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProducto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.findById(id)
                .map(productoExistente -> {
                    // Verificar que la categoría existe
                    if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
                        categoriaService.findById(producto.getCategoria().getId())
                            .ifPresent(producto::setCategoria);
                    } else {
                        producto.setCategoria(productoExistente.getCategoria());
                    }
                    
                    producto.setId(id);
                    return ResponseEntity.ok(productoService.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!productoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}