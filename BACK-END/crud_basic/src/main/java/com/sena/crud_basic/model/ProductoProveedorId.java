package com.sena.crud_basic.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoProveedorId implements Serializable {

    private Long productoId;
    private Long proveedorId;

    public ProductoProveedorId() {
    }

    public ProductoProveedorId(Long productoId, Long proveedorId) {
        this.productoId = productoId;
        this.proveedorId = proveedorId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoProveedorId)) return false;
        ProductoProveedorId that = (ProductoProveedorId) o;
        return Objects.equals(productoId, that.productoId) &&
               Objects.equals(proveedorId, that.proveedorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, proveedorId);
    }
}
