package com.sena.crud_basic.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.Categoria;
import com.sena.crud_basic.model.Producto;
import com.sena.crud_basic.repository.ProductoRepository;;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }
    
    public List<Producto> findByCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }
    
    public List<Producto> findByNombreContaining(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
    
    public List<Producto> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax) {
        return productoRepository.findByPrecioBetween(precioMin, precioMax);
    }
    
    public List<Producto> findByStockBajo(int stockMinimo) {
        return productoRepository.findByStockLessThan(stockMinimo);
    }
    
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}