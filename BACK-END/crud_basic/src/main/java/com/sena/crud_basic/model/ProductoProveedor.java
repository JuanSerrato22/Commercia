package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_proveedor")
public class ProductoProveedor {

    @EmbeddedId
    private ProductoProveedorId id = new ProductoProveedorId();

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @MapsId("proveedorId")
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    public ProductoProveedor() {
    }

    public ProductoProveedor(Producto producto, Proveedor proveedor) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.id = new ProductoProveedorId(producto.getId(), proveedor.getId());
    }

    public ProductoProveedorId getId() {
        return id;
    }

    public void setId(ProductoProveedorId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
