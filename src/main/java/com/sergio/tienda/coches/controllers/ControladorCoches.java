package com.sergio.tienda.coches.controllers;

import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/")
public class ControladorCoches {

    @Autowired
    private ServicioCoches servicioCoches;




    @RequestMapping("gestionarCoches")
    public String gestionarCoches(Model model){

        model.addAttribute("coches", servicioCoches.obtenerCoches());

        return "admin/gestionar_productos";
    }





    @RequestMapping("registrarCoche")
    public String registrarCoche(Model model){

        Coche nuevo = new Coche();
        model.addAttribute("nuevo", nuevo);

        return "admin/registro_producto";
    }//END REGISTRARCOCHE



    @RequestMapping("guardarCoche")
    public String guardarCoche(@ModelAttribute("nuevo") @Valid Coche nuevo, BindingResult resultadoValidacion, Model model){
        System.out.println("recibido para registrar: " + nuevo);

        if (resultadoValidacion.hasErrors()){
            return "admin/registro_producto";
        }

        servicioCoches.registrarCoche(nuevo);

        return "admin/registro_producto_ok";
    }//END GUARDARCOCHE




    @RequestMapping("borrarCoche")
    public String borrarCoche(@RequestParam("id") long id, Model model) throws Exception {

        System.out.println("id recibida para borrar registro: " + id);

        servicioCoches.borrarCoche(id);

        return gestionarCoches(model);
    }//END BORRAR_VIDEOJUEGO




    @RequestMapping("editarCoche")
    public String editarCoche(@RequestParam("id") Long id, Model model){
        model.addAttribute("cocheEditar", servicioCoches.obtenerCochePorId(id));
        return "admin/editar_producto";
    }// END EDITAR_COCHE



    @RequestMapping("guardarCambiosCoche")
    public String guardarCambiosCoche(
            @ModelAttribute("cocheEditar")
            @Valid
            Coche cocheEditar,
            BindingResult resultadoValidacion,
            Model model)throws Exception{

        if (resultadoValidacion.hasErrors()){
            return "admin/editar_producto";
        }else{
            servicioCoches.guardarCambiosCoche(cocheEditar);
        }
        return gestionarCoches(model);
    }// END GUARDAR_CAMBIOS_COCHE









}
