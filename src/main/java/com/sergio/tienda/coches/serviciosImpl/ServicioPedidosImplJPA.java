package com.sergio.tienda.coches.serviciosImpl;

import com.sergio.tienda.coches.constantesPedidos.EstadosPedido;
import com.sergio.tienda.coches.constantesSQL.ConstantesSQL;
import com.sergio.tienda.coches.model.*;
import com.sergio.tienda.coches.responseData.ResumenPedido;
import com.sergio.tienda.coches.servicios.ServicioCarrito;
import com.sergio.tienda.coches.servicios.ServicioPedidos;
import com.sergio.tienda.coches.model.Pedido;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServicioPedidosImplJPA implements ServicioPedidos {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServicioCarrito servicioCarrito;

    @Override
    public List<Pedido> obtenerPedidos() {

        return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();
    }

    @Override
    public Pedido obtenerPedidoPorId(long id) {
        return entityManager.find(Pedido.class, id);
    }



    @Override
    public void actualizarEstadoPedido(long id, String estado) {
        Pedido p = entityManager.find(Pedido.class, id);
        p.setEstado(estado);
        entityManager.merge(p);
    }

    // operaciones con la parte publica

    @Override
    public void procesarPaso1(String nombreCompleto, String direccion, String provincia, String codigoPostal,
                              String telefono, String ciudad, long idUsuario) {
        //cada usuario podra tener como maximo un solo
        //pedido en estado "en proceso"
        //ese pedido es donde rellenaremos los datos de envio,
        //pago y demas, si hubiera
        //si el usuario finaliza el pedido, el estado del pedido
        //pasa a ser "completado"
        //puede haber tantos pedidos "completado" como se quiera
        Pedido p = obtenerPedidoActual(idUsuario);
        p.setNombreCompleto(nombreCompleto);
        p.setDireccion(direccion);
        p.setProvincia(provincia);
        p.setCodigoPostal(codigoPostal);
        p.setTelefono(telefono);
        p.setCiudad(ciudad);
        entityManager.merge(p);//actualiza o registra
    }

    @Override
    public void procesarPaso2(String titular, String numero, String tipoTarjeta, String fechaCaducidad,
                              String cvv, long idUsuario) {
        Pedido p = obtenerPedidoActual(idUsuario);
        p.setTitularTarjeta(titular);
        p.setNumeroTarjeta(numero);
        p.setTipoTarjeta(tipoTarjeta);
        p.setFechaCaducidad(fechaCaducidad);
        p.setCvv(cvv);
        entityManager.merge(p);
    }

    @Override
    public void procesarPaso3(String tipoEntrega, String concesionarioSeleccionado, Boolean requiereMatriculacion, String fechaPreferidaEntrega, long idUsuario) {
        Pedido p = obtenerPedidoActual(idUsuario);
        p.setTipoEntrega(tipoEntrega);
        p.setConcesionarioSeleccionado(concesionarioSeleccionado);
        p.setRequiereMatriculacion(requiereMatriculacion);
        p.setFechaPreferidaEntrega(fechaPreferidaEntrega);
        entityManager.merge(p);
    }

    @Override
    public ResumenPedido obtenerResumenPedido(long idUsuario) {
        Pedido p = obtenerPedidoActual(idUsuario);
        ResumenPedido resumen = new ResumenPedido();
        resumen.setNombreCompleto(p.getNombreCompleto());
        resumen.setDireccion(p.getDireccion());
        resumen.setProvincia(p.getProvincia());
        resumen.setTipoTarjeta(p.getTipoTarjeta());
        resumen.setTitularTarjeta(p.getTitularTarjeta());
        resumen.setNumeroTarjeta(p.getNumeroTarjeta());
        resumen.setCiudad(p.getCiudad());
        resumen.setCodigoPostal(p.getCodigoPostal());
        resumen.setConcesionarioSeleccionado(p.getConcesionarioSeleccionado());
        resumen.setCvv(p.getCvv());
        resumen.setFechaCaducidad(p.getFechaCaducidad());
        resumen.setFechaPreferidaEntrega(p.getFechaPreferidaEntrega());
        resumen.setRequiereMatriculacion(p.getRequiereMatriculacion());
        resumen.setTelefono(p.getTelefono());
        resumen.setTipoEntrega(p.getTipoEntrega());
        resumen.setCoches(
                servicioCarrito.obtenerProductosCarrito(idUsuario));
        return resumen;
    }

    @Override
    public void confirmarPedido(long idUsuario) {
        Pedido p = obtenerPedidoActual(idUsuario);
        Carrito c = entityManager.createQuery(
                        "SELECT c FROM Carrito c WHERE c.usuario.id = :idUsuario", Carrito.class)
                .setParameter("idUsuario", idUsuario)
                .getSingleResult();
        //pasar los productos del carrito al pedido
        if( c != null && c.getProductosCarrito().size() > 0){
            for(ProductoCarrito pc : c.getProductosCarrito()){
                ProductoPedido pp = new ProductoPedido();
                pp.setCantidad(pc.getCantidad());
                pp.setCoche(pc.getCoche());
                p.getProductosPedido().add(pp);
                pp.setPedido(p);
                entityManager.persist(pp);
            }
        }
        //borrar los productos del carrito del usuario
        entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO).setParameter("carrito_id", c.getId()).executeUpdate();
        p.setEstado(EstadosPedido.Completado.toString());
        entityManager.merge(p);
    }

    private Pedido obtenerPedidoActual(long idUsuario) {
        //vamos a pedir del usuario actual todos los pedidos "en proceso"
        Query consulta =
                entityManager.createQuery(
                        "select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id"
                ).setParameter("estado", EstadosPedido.EnProceso.name())
                        .setParameter("usuario_id", idUsuario);
        List<Pedido> resultadoConsulta = consulta.getResultList();
        if(resultadoConsulta.size()==1){
            return resultadoConsulta.get(0);
        }
        //si el metodo sigue para adelante hay que crear un nuevo
        //pedido con el estado en proceso
        Usuario uBaseDatos = entityManager.find(Usuario.class, idUsuario);
        Pedido pedidoEnProceso = new Pedido();
        pedidoEnProceso.setUsuario(uBaseDatos);
        pedidoEnProceso.setEstado(EstadosPedido.EnProceso.name());
        return pedidoEnProceso;
    }
}










