package com.sergio.tienda.coches.controladorImagen;


import com.sergio.tienda.coches.servicios.ServicioCoches;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ControladorImagen {

    @Autowired
    private ServicioCoches servicioCoches;


    @RequestMapping("mostrar_imagen")
    public void mostrarImagen(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        byte[] infoImagenPortada = servicioCoches.obtenerCochePorId(id).getImagenPortada();
        //byte[] infoImagen2 = servicioCoches.obtenerCochePorId(id).getImagen2();
        //byte[] infoImagen3 = servicioCoches.obtenerCochePorId(id).getImagen3();

        if (infoImagenPortada == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagenPortada);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN



    @RequestMapping("mostrar_imagen2")
    public void mostrarImagen2(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        //byte[] infoImagenPortada = servicioCoches.obtenerCochePorId(id).getImagenPortada();
        byte[] infoImagen2 = servicioCoches.obtenerCochePorId(id).getImagen2();
        //byte[] infoImagen3 = servicioCoches.obtenerCochePorId(id).getImagen3();

        if (infoImagen2 == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagen2);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN2


    @RequestMapping("mostrar_imagen3")
    public void mostrarImagen3(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        //byte[] infoImagenPortada = servicioCoches.obtenerCochePorId(id).getImagenPortada();
        //byte[] infoImagen2 = servicioCoches.obtenerCochePorId(id).getImagen2();
        byte[] infoImagen3 = servicioCoches.obtenerCochePorId(id).getImagen3();

        if (infoImagen3 == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagen3);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN3





    @RequestMapping("mostrar_imagen4")
    public void mostrarImagen4(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        //byte[] infoImagenPortada = servicioCoches.obtenerCochePorId(id).getImagenPortada();
        //byte[] infoImagen2 = servicioCoches.obtenerCochePorId(id).getImagen2();
        byte[] infoImagen4 = servicioCoches.obtenerCochePorId(id).getImagen4();

        if (infoImagen4 == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagen4);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN4



    @RequestMapping("mostrar_imagen5")
    public void mostrarImagen5(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        //byte[] infoImagenPortada = servicioCoches.obtenerCochePorId(id).getImagenPortada();
        //byte[] infoImagen2 = servicioCoches.obtenerCochePorId(id).getImagen2();
        byte[] infoImagen5 = servicioCoches.obtenerCochePorId(id).getImagen5();

        if (infoImagen5 == null){
            return;
        }

        //emitir la info
        response.setContentType("image/jpg, image/jpeg, image/png, image/gif, image/webp");
        response.getOutputStream().write(infoImagen5);
        response.getOutputStream().close();


    }// END MOSTRAR_IMAGEN5



}
