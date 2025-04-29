package com.sena.crud_basic.service;

import com.sena.crud_basic.model.Proveedor;
import com.sena.crud_basic.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Listar todos los proveedores
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    // Guardar o actualizar un proveedor
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Buscar un proveedor por ID
    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    // Eliminar un proveedor
    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}
