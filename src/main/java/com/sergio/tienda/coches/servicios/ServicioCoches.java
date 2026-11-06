package com.sergio.tienda.coches.servicios;

import com.sergio.tienda.coches.model.Coche;

import java.util.List;
import java.util.Map;


public interface ServicioCoches {

    void registrarCoche(Coche c);

    List<Coche> obtenerCoches();

    List<Coche> obtenerCochePorMarca(String marca);

    List<Coche> obtenerCochePorMarcaYcomienzoYfin(String marca, int comienzo,int resultadosPorPagina);

    Coche obtenerCochePorId(long id);

    void borrarCoche(long id) throws Exception;

    void guardarCambiosCoche(Coche cocheEditar) throws Exception;

    List<Map<String, Object>> obtenerJSONcoches(String marca, int comienzo);
    
    Map<String, Object> obtenerJSONCochePorId(Long id);

    int obtenerTotalCoches(String marca);


}
