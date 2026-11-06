package com.sergio.tienda.coches.servicios;

import com.sergio.tienda.coches.model.Pedido;
import com.sergio.tienda.coches.responseData.ResumenPedido;

import java.util.List;

public interface ServicioPedidos {

    //gestion en administracion
    List<Pedido> obtenerPedidos();
    Pedido obtenerPedidoPorId(long id);
    void actualizarEstadoPedido(long id, String estado);

    //operaciones para la parte publica - ajax/api rest
    void procesarPaso1(String nombreCompleto, String direccion,
                       String provincia, String codigoPostal,
                       String telefono, String ciudad, long idUsuario);

    void procesarPaso2(String titular, String numero,
                       String tipoTarjeta, String fechaCaducidad,
                       String cvv,long idUsuario);

    void procesarPaso3(String tipoEntrega, String concesionarioSeleccionado,
                       Boolean requiereMatriculacion, String fechaPreferidaEntrega,
                       long idUsuario);
    ResumenPedido obtenerResumenPedido(long idUsuario);
    void confirmarPedido(long idUsuario);

}











