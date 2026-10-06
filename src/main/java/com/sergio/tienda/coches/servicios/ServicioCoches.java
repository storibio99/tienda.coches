package com.sergio.tienda.coches.servicios;

import com.sergio.tienda.coches.model.Coche;

import java.util.List;


public interface ServicioCoches {

    void registrarCoche(Coche c);

    List<Coche> obtenerCoches();

    Coche obtenerCochePorId(long id);

    void borrarCoche(long id) throws Exception;

    void guardarCambiosCoche(Coche cocheEditar) throws Exception;
}
