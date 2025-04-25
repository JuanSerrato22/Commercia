package com.sena.crud_basic.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Categoria;
import com.sena.crud_basic.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax);
    List<Producto> findByStockLessThan(int stockMinimo);
}