package com.sergio.tienda.coches.controladorImagen;


import com.sergio.tienda.coches.servicios.ServicioCategorias;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import com.sergio.tienda.coches.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ControladorImagenUsuario {

    @Autowired
    private ServicioUsuarios servicioUsuarios;



    @RequestMapping("mostrar_imagen_usuario")
    public void mostrar_imagen_usuario(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        byte[] infoImagenPortada = servicioUsuarios.obtenerUsuarioPorId(id).getAvatar();


        if (infoImagenPortada == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagenPortada);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN







}
