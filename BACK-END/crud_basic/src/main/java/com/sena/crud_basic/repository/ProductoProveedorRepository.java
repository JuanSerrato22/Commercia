package com.sena.crud_basic.repository;

import com.sena.crud_basic.model.ProductoProveedor;
import com.sena.crud_basic.model.ProductoProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, ProductoProveedorId> {
    // Aquí puedes agregar consultas personalizadas si las necesitas luego
}
