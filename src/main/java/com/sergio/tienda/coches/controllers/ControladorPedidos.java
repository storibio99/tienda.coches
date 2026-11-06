package com.sergio.tienda.coches.controllers;


import com.sergio.tienda.coches.constantesPedidos.EstadosPedido;
import com.sergio.tienda.coches.model.Pedido;
import com.sergio.tienda.coches.servicios.ServicioPedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin/")
public class ControladorPedidos {

    @Autowired
    private ServicioPedidos servicioPedidos;


    @RequestMapping("gestionarPedidos")
    public String gestionarCoches(Model model){

        model.addAttribute("pedidos", servicioPedidos.obtenerPedidos());
        return "admin/gestionar_pedidos";
    }


    @RequestMapping("verDetallesPedido")
    public String verDetallesPedido(@RequestParam("id") Long id, Model model){

        Pedido p = servicioPedidos.obtenerPedidoPorId(id);
        model.addAttribute("pedido",p);

        //vamos a meter los estados en model
        //para formar un desplegable en pedidos html
        Map<String, String> estados = new HashMap<String, String>();
        estados.put(EstadosPedido.Completado.name(), "completado por el usuario");
        estados.put(EstadosPedido.Enviado.name(), "enviado a la empresa de mensajeria");
        estados.put(EstadosPedido.Incidencia.name(), "incidencia en el pedido");
        model.addAttribute("estados", estados);

        return "admin/pedido_detalle";
    }



}
