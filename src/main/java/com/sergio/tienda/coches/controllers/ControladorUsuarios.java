package com.sergio.tienda.coches.controllers;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.servicios.ServicioCategorias;
import com.sergio.tienda.coches.servicios.ServicioUsuarios;
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
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @RequestMapping("gestionarUsuarios")
    public String gestionarUsuarios(Model model){

        model.addAttribute("usuarios", servicioUsuarios.obtenerUsuarios());

        return "admin/usuarios/gestionar_usuarios";
    }// END GESTIONAR_USUARIOS




    @RequestMapping("editarUsuario")
    public String editarUsuario(@RequestParam("id") Long id, Model model){

        Usuario uEditar = servicioUsuarios.obtenerUsuarioPorId(id);

        model.addAttribute("usuarioEditar", uEditar);

        return "admin/usuarios/editar_usuario";
    }// END EDITAR_USUARIO



    @RequestMapping("guardarCambiosUsuario")
    public String guardarCambiosUsuario(
            @ModelAttribute("usuarioEditar")
            @Valid
            Usuario usuarioEditar,
            BindingResult resultadoValidacion,
            Model model)throws Exception{

        if (resultadoValidacion.hasErrors()){
            return "admin/usuarios/editar_usuarios";
        }else{
            servicioUsuarios.guardarCambiosUsuario(usuarioEditar);
        }
        return gestionarUsuarios(model);
    }// END GUARDAR_CAMBIOS_USUARIO



    @RequestMapping("registrarUsuario")
    public String registrarUsuario(Model model){

        Usuario nuevo = new Usuario();
        model.addAttribute("nuevo", nuevo);


        return "admin/usuarios/registro_usuario";
    }//END REGISTRARUSUARIO



    @RequestMapping("guardarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevo") @Valid Usuario nuevo, BindingResult resultadoValidacion, Model model){
        System.out.println("recibido para registrar: " + nuevo);

        if (resultadoValidacion.hasErrors()){
            return "admin/usuarios/registro_usuario";
        }

        servicioUsuarios.registrarUsuario(nuevo);

        return "admin/usuarios/registro_usuario_ok";
    }//END GUARDARUSUARIO



    @RequestMapping("borrarUsuario")
    public String borrarUsuario(@RequestParam("id") long id, Model model) throws Exception {

        System.out.println("id recibida para borrar registro: " + id);

        servicioUsuarios.borrarUsuario(id);

        return gestionarUsuarios(model);
    }//END BORRAR_COCHE



}
