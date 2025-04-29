package com.sena.crud_basic.service;

import com.sena.crud_basic.model.ProductoProveedor;
import com.sena.crud_basic.model.ProductoProveedorId;
import com.sena.crud_basic.repository.ProductoProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoProveedorService {

    @Autowired
    private ProductoProveedorRepository productoProveedorRepository;

    public List<ProductoProveedor> listarTodos() {
        return productoProveedorRepository.findAll();
    }

    public Optional<ProductoProveedor> obtenerPorId(ProductoProveedorId id) {
        return productoProveedorRepository.findById(id);
    }

    public ProductoProveedor guardar(ProductoProveedor productoProveedor) {
        return productoProveedorRepository.save(productoProveedor);
    }

    public void eliminar(ProductoProveedorId id) {
        productoProveedorRepository.deleteById(id);
    }
}
