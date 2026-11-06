package com.sergio.tienda.coches.servicios;

import java.util.List;
import java.util.Map;

public interface ServicioCarrito {

    void agregarProducto(long idUsuario, long idProducto, int cantidad);

    //operaciones que devuelven json:
    List<Map<String, Object>> obtenerProductosCarrito(long idUduario);





}
