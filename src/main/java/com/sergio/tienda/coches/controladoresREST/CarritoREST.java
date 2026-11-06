package com.sergio.tienda.coches.controladoresREST;


import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.servicios.ServicioCarrito;
import com.sergio.tienda.coches.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("restCarrito/")
@RestController
public class CarritoREST {

    @Autowired
    private ServicioCarrito servicioCarrito;


    @RequestMapping("agregarProducto")
    private String agregarProducto(
            @RequestParam("id") long id,
            @RequestParam("cantidad") int cantidad,
            HttpServletRequest request){
        String respuesta = "";

        //comprobar que el usuario este identificado

        //la info del usuario esta metida en una variable de sesion
        //consultar UsuariosREST/identificarUsuario
        Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
        respuesta = "producto agregado(consultar h2): de id: " + id + " cantidad: " + cantidad + " al usuario de nombre " + u.getNombre();
        servicioCarrito.agregarProducto(u.getId(), id, cantidad);

        return respuesta;
    }//END AGREGAR_CARRITO



    @RequestMapping("obtenerProductos")
    public List<Map<String, Object>> obtenerProductos(HttpServletRequest request){

        return servicioCarrito.obtenerProductosCarrito(
                ((Usuario)request.getSession().getAttribute("usuario_identificado")).getId()
        );

    }// END OBTENER_PRODUCTOS

















}// END CLASS
