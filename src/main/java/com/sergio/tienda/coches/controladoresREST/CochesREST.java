package com.sergio.tienda.coches.controladoresREST;


import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.responseData.InfoCoches;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//necesario para listar y ver detalles en la parte publica

// esto sirve para generar el json
@RestController
@RequestMapping("restCoches/")
public class CochesREST {

    private final ServicioCoches servicioCoches;

    public CochesREST(ServicioCoches servicioCoches) {
        this.servicioCoches = servicioCoches;
    }

//    @RequestMapping("listar")
//    public List<Coche> listar(){
//        return servicioCoches.obtenerCoches();
//    }// FIN LISTAR
    //ahora que se ha complicado un poco
    //vamos a tomar mas control en devolver el json con el listado de coches

    @RequestMapping("listar")
    public InfoCoches listar(
            @RequestParam(name = "marca", defaultValue = "") String marca,
            @RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo){

        int totalCoches = servicioCoches.obtenerTotalCoches(marca);
        List<Map<String, Object>> coches = servicioCoches.obtenerJSONcoches(marca, comienzo);
        //ahora necesitamos devolverle al js estas 2 cosas
        //para ello vamos a crear un nuevo tipo de dato
        InfoCoches info = new InfoCoches();
        info.setTotalCoches(totalCoches);
        info.setCoches(coches);
        return info;


        //return servicioCoches.obtenerJSONcoches(marca, comienzo);
    }// END LISTAR


    @RequestMapping("obtener-coche")
    public Map<String, Object> obtenerCoche(@RequestParam("id") Long id){
        return servicioCoches.obtenerJSONCochePorId(id);
    }// FIN OBTENER COCHE




}
