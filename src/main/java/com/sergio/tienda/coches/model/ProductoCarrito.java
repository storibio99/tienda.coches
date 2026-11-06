package com.sergio.tienda.coches.model;


import jakarta.persistence.*;

@Entity
public class ProductoCarrito {


    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Coche coche;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;


    //campos extra
    private int cantidad;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
