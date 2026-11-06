package com.sergio.tienda.coches.controladoresREST.admin;


import com.sergio.tienda.coches.servicios.ServicioPedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/restPedido/")
public class PedidosRestAdmin {

    @Autowired
    private ServicioPedidos servicioPedidos;


    @RequestMapping("actualizarEstadoPedido")
    public String actualizarEstadoPedido(@RequestParam("id") Integer id, String estado){
        servicioPedidos.actualizarEstadoPedido(id, estado);
        return "ok";
    }

}
