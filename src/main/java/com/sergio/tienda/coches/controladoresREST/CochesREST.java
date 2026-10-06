package com.sergio.tienda.coches.controladoresREST;


import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// esto sirve para generar el json
@RestController
@RequestMapping("restCoches/")
public class CochesREST {

    private final ServicioCoches servicioCoches;

    public CochesREST(ServicioCoches servicioCoches) {
        this.servicioCoches = servicioCoches;
    }

    @RequestMapping("listar")
    public List<Coche> listar(){
        return servicioCoches.obtenerCoches();
    }// FIN LISTAR


    @RequestMapping("obtener-coche")
    public Coche obtenerCoche(@RequestParam("id") Long id){
        return servicioCoches.obtenerCochePorId(id);
    }// FIN OBTENER COCHE




}
