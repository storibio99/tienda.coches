package com.sergio.tienda.coches.model;


import jakarta.persistence.*;


@Entity
public class ProductoPedido {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Coche coche;

    private int cantidad;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
