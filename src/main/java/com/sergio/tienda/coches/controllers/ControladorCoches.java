package com.sergio.tienda.coches.controllers;

import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCategorias;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import com.sergio.tienda.coches.validadorImagen.ValidadorImagen;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("admin/")
public class ControladorCoches {

    @Autowired
    private ServicioCoches servicioCoches;
    @Autowired
    private ServicioCategorias servicioCategorias;




    @RequestMapping("gestionarCoches")
    public String gestionarCoches(
            @RequestParam( name = "marca", defaultValue = "") String marca,
            @RequestParam(value = "comienzo",defaultValue = "0") Integer comienzo,
            Model model){
        model.addAttribute("coches",
                servicioCoches.obtenerCochePorMarcaYcomienzoYfin(
                        marca, comienzo, 10));
        //vamos a pasarle la variable siguiente para que la vista
        //sepa como formar el enlace siguiente
        model.addAttribute("siguiente", comienzo + 10);
        model.addAttribute("anterior", comienzo - 10);
        model.addAttribute("total",
                servicioCoches.obtenerTotalCoches(marca));
        model.addAttribute("marca", marca);
        return "admin/gestionar_productos";
    }





    @RequestMapping("registrarCoche")
    public String registrarCoche(Model model){

        Coche nuevo = new Coche();
        model.addAttribute("nuevo", nuevo);

        //necesito obtener las categorias
        //para meterlas en este model de spring
        //y que le llegen al html del return
        model.addAttribute("categorias", servicioCategorias.obtenerCategorias());

        return "admin/registro_producto";
    }//END REGISTRARCOCHE



    @RequestMapping("/guardarCoche")
    public String guardarCoche(@Valid @ModelAttribute("nuevo") Coche coche,
                               BindingResult result,
                               Model model) throws IOException {

        MultipartFile[] fotos = {
                coche.getFotoSubida(), coche.getFotoSubida2(), coche.getFotoSubida3(),
                coche.getFotoSubida4(), coche.getFotoSubida5()
        };
        String[] campos = {"fotoSubida", "fotoSubida2", "fotoSubida3", "fotoSubida4", "fotoSubida5"};

        for (int i = 0; i < fotos.length; i++) {
            String error = ValidadorImagen.validar(fotos[i]);
            if (error != null) {
                result.rejectValue(campos[i], "imagen.invalida", error);
            }
        }

        if (result.hasErrors()) {
            // aquí vuelve a cargar lo que necesite el formulario (lista de categorías, etc.)
            // model.addAttribute("categorias", servicioCategorias.obtenerTodas());
            return "admin/registro_producto";   // el nombre de tu plantilla
        }

        // Si llega aquí, todas las imágenes son válidas
        if (!coche.getFotoSubida().isEmpty())  coche.setImagenPortada(coche.getFotoSubida().getBytes());
        if (!coche.getFotoSubida2().isEmpty()) coche.setImagen2(coche.getFotoSubida2().getBytes());
        if (!coche.getFotoSubida3().isEmpty()) coche.setImagen3(coche.getFotoSubida3().getBytes());
        if (!coche.getFotoSubida4().isEmpty()) coche.setImagen4(coche.getFotoSubida4().getBytes());
        if (!coche.getFotoSubida5().isEmpty()) coche.setImagen5(coche.getFotoSubida5().getBytes());

        // servicioCoches.guardar(coche);
        return "admin/gestionar_producto";
    }//END GUARDAR_COCHE




    @RequestMapping("borrarCoche")
    public String borrarCoche(@RequestParam("id") long id, Model model) throws Exception {

        System.out.println("id recibida para borrar registro: " + id);

        servicioCoches.borrarCoche(id);

        return gestionarCoches("",0,model);
    }//END BORRAR_COCHE




    @RequestMapping("editarCoche")
    public String editarCoche(@RequestParam("id") Long id, Model model){

        Coche cEditar = servicioCoches.obtenerCochePorId(id);
        cEditar.setIdCategoria(cEditar.getCategoria().getId());

        model.addAttribute("cocheEditar", cEditar);
        model.addAttribute("categorias", servicioCategorias.obtenerCategorias());

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
        return gestionarCoches("",0,model);
    }// END GUARDAR_CAMBIOS_COCHE









}
