package com.sergio.tienda.coches.controladoresREST;


import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.responseData.ResumenPedido;
import com.sergio.tienda.coches.servicios.ServicioCarrito;
import com.sergio.tienda.coches.servicios.ServicioPedidos;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("restPedido/")
@RestController
public class PedidosREST {

    @Autowired
    private ServicioPedidos servicioPedidos;

    @RequestMapping("procesarPaso1")
    public String paso1(String nombre, String direccion, String provincia, String codigoPostal, String telefono, String ciudad, HttpServletRequest request){

        Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
        servicioPedidos.procesarPaso1(nombre, direccion, provincia, codigoPostal, telefono, ciudad, u.getId());

        return "ok";
    }

    @RequestMapping("procesarPaso2")
    public String paso2(String titular, String numero, String tarjeta, String caducidad, String cvv, HttpServletRequest request){

        Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
        servicioPedidos.procesarPaso2(titular, numero, tarjeta, caducidad, cvv, u.getId());

        return "ok";
    }

    @RequestMapping("procesarPaso3")
    public ResumenPedido paso3(String tipoEntrega, String concesionarioSeleccionado, Boolean requiereMatriculacion, String fechaPreferidaEntrega, HttpServletRequest request){

        Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
        servicioPedidos.procesarPaso3(tipoEntrega, concesionarioSeleccionado, requiereMatriculacion, fechaPreferidaEntrega, u.getId());

        return servicioPedidos.obtenerResumenPedido(u.getId());
    }


    @RequestMapping("procesarPaso4")
    public String procesarPaso4(HttpServletRequest request){

        Usuario u = (Usuario) request.getSession().getAttribute("usuario_identificado");
        servicioPedidos.confirmarPedido(u.getId());

        return "ok";
    }







}
