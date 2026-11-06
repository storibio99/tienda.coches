package com.sergio.tienda.coches.controllers;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCategorias;
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
public class ControladorCategorias {

    @Autowired
    private ServicioCategorias servicioCategorias;

    @RequestMapping("gestionarCategorias")
    public String gestionarCategorias(Model model){

        model.addAttribute("categorias", servicioCategorias.obtenerCategorias());

        return "admin/categorias/gestionar_categorias";
    }// END GESTIONAR_CATEGORIAS




    @RequestMapping("editarCategoria")
    public String editarCategoria(@RequestParam("id") Long id, Model model){

        Categoria cEditar = servicioCategorias.obtenerCategoriaPorId(id);

        model.addAttribute("categoriaEditar", cEditar);

        return "admin/categorias/editar_categoria";
    }// END EDITAR_CATEGORIA



    @RequestMapping("guardarCambiosCategoria")
    public String guardarCambiosCategoria(
            @ModelAttribute("categoriaEditar")
            @Valid
            Categoria categoriaEditar,
            BindingResult resultadoValidacion,
            Model model)throws Exception{

        if (resultadoValidacion.hasErrors()){
            return "admin/categorias/editar_categoria";
        }else{
            servicioCategorias.guardarCambiosCategoria(categoriaEditar);
        }
        return gestionarCategorias(model);
    }// END GUARDAR_CAMBIOS_CATEGORIA



    @RequestMapping("registrarCategoria")
    public String registrarCategoria(Model model){

        Categoria nuevo = new Categoria();
        model.addAttribute("nuevo", nuevo);


        return "admin/categorias/registro_categoria";
    }//END REGISTRARCATEGORIA



    @RequestMapping("guardarCategoria")
    public String guardarCategoria(@ModelAttribute("nuevo") @Valid Categoria nuevo, BindingResult resultadoValidacion, Model model)  throws IOException {
        System.out.println("recibido para registrar: " + nuevo);

        MultipartFile[] fotos = {
                nuevo.getFotoSubidaCategoria()
        };

        String[] campos = {"fotoSubida"};

        for (int i = 0; i < fotos.length; i++) {
            String error = ValidadorImagen.validar(fotos[i]);
            if (error != null) {
                resultadoValidacion.rejectValue(campos[i], "imagen.invalida", error);
            }
        }

        if (resultadoValidacion.hasErrors()){
            return "admin/categorias/registro_categoria";
        }

        // Si llega aquí, todas las imágenes son válidas
        if (!nuevo.getFotoSubidaCategoria().isEmpty())  nuevo.setImagenCategoria(nuevo.getFotoSubidaCategoria().getBytes());

        servicioCategorias.registrarCategoria(nuevo);

        return "admin/categorias/registro_categoria_ok";
    }//END GUARDARCATEGORIA



    @RequestMapping("borrarCategoria")
    public String borrarCategoria(@RequestParam("id") long id, Model model) throws Exception {

        System.out.println("id recibida para borrar registro: " + id);

        servicioCategorias.borrarCategoria(id);

        return gestionarCategorias(model);
    }//END BORRAR_COCHE



}
