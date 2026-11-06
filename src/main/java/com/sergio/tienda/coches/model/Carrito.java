package com.sergio.tienda.coches.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario usuario;


    @OneToMany(mappedBy = "carrito")
    private List<ProductoCarrito> productosCarrito = new ArrayList<ProductoCarrito>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }
}
