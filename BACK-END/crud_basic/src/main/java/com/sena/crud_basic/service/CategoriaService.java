package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.Categoria;
import com.sena.crud_basic.repository.CategoriaRepository;;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }
    
    public Optional<Categoria> findByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }
    
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}