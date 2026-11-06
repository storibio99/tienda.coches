package com.sergio.tienda.coches.controladoresREST;

//necesario para registrar/identificar usuarios en la parte publica

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("restUsuarios/")
public class UsuariosREST {

    @Autowired
    private ServicioUsuarios servicioUsuarios;


    @RequestMapping("registrarUsuario")
    public String registrarUsuario(
            @RequestParam Map<String, Object> formData,
            MultipartHttpServletRequest multipartRequest
    ){
        System.out.println("Recibido formData: " + formData);
        //formData tiene la info del usuario a registrar
        //transformar el formData a Usuario
        //vamos a hacerlo usando una libreria llamada gson
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(formData);
        Usuario u = gson.fromJson(json, Usuario.class);

        //voy a extraer del form data la imagen para asignarla
        //al avatar de usuario
        try {
            u.setAvatar(multipartRequest.getFile("avatar").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        servicioUsuarios.registrarUsuario(u);

        return "te has registrado correctamente";
    }//END REGISTRAR_USUARIO


    //recordatorio, todos estos metodos se llaman desde el js de index.html
    @RequestMapping("identificarUsuario")
    public String identificarUsuario(
            @RequestParam("email") String email,
            @RequestParam("pass") String pass,
            HttpServletRequest request){

        //comprobar si hay un usuario con este email y pass en bd
        //si es asi los datos son correctos
        //marcamos al usuario en cuestion que esta identificado
        //usando una variable de sesion como hicimos con
        // el interceptor de administracion
        Usuario u = servicioUsuarios.obtenerUsuarioPorEmailYpass(email, pass);
        String respuesta = "";

        if (u != null){
            request.getSession().setAttribute("usuario_identificado", u);
            respuesta = "ok," + u.getNombre() + "," + u.getId();

        }else{
            respuesta = "email o pass incorrectos";
        }

        return respuesta;
    }//END IDENTIFICAR_USUARIO


    @RequestMapping("cerrarSesion")
    public String cerrarSesion(HttpServletRequest request){
        request.getSession().invalidate();
        return "ok";
    }



}
