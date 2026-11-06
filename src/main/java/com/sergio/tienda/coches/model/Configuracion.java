package com.sergio.tienda.coches.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


//tabla que va a ayudarnos a ver si ya tenemos unos registros iniciales u otras cosas
@Entity
public class Configuracion {

    // la tabla configuracion marcara en una fila si ya se han insertado
    // los registros iniciales de la tienda
    private boolean registrosIniciales;

    @Id
    @GeneratedValue
    private int id;





    public boolean isRegistrosIniciales() {
        return registrosIniciales;
    }

    public void setRegistrosIniciales(boolean registrosIniciales) {
        this.registrosIniciales = registrosIniciales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
