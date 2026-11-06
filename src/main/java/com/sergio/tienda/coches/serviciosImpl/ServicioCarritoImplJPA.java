package com.sergio.tienda.coches.serviciosImpl;


import com.sergio.tienda.coches.constantesSQL.ConstantesSQL;
import com.sergio.tienda.coches.model.Carrito;
import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.model.ProductoCarrito;
import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.servicios.ServicioCarrito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ServicioCarritoImplJPA implements ServicioCarrito {

    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public void agregarProducto(long idUsuario, long idProducto, int cantidad) {
        Usuario uBaseDatos =
                entityManager.find(Usuario.class, idUsuario);
        //esto da problemas
        //Carrito c = uBaseDatos.getCarrito();
        //esto es mas seguro:
        List<Carrito> resCarrrito = entityManager.createQuery("SELECT c FROM Carrito c WHERE c.usuario.id = :idUsuario", Carrito.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList();
        Carrito c = null;
        if (resCarrrito.size()==0){
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setUsuario(uBaseDatos);
            entityManager.persist(nuevoCarrito);
            c = nuevoCarrito;
        }else{
            c = resCarrrito.get(0);
        }

        if ( c == null ){
            //si el usuario no tiene un carrito asociado, lo creamos
            // y lo guardamos en BD
            c = new Carrito();
            c.setUsuario(uBaseDatos);
            entityManager.persist(c);
        }
        //ver si el producto que se quiere agregar ya esta en el carrito
        //si lo esta, hay que incrementar su cantidad
        boolean productoEnCarrito = false;
        for(ProductoCarrito pc : c.getProductosCarrito()){
            if(pc.getCoche().getId() == idProducto){
                //el producto ya esta en el carrito
                productoEnCarrito = true;
                pc.setCantidad(pc.getCantidad() + cantidad);
                entityManager.merge(pc);
            }
        }
        //si el producto no esta en el carrito, creamos un nuevo ProductoCarrito
        if( ! productoEnCarrito ){
            ProductoCarrito pc = new ProductoCarrito();
            pc.setCarrito(c);
            pc.setCoche( entityManager.find(Coche.class, idProducto) );
            pc.setCantidad(cantidad);
            entityManager.persist(pc);
        }
    }




    @Override
    public List<Map<String, Object>> obtenerProductosCarrito(long idUsuario) {

        //sacar los productos del carrito a partir de la id de usuario
        //esto no es fiable
        //Usuario u = entityManager.find(Usuario.class, idUsuario);
        //Carrito c = u.getCarrito();
        //mejor asi

        Carrito c = entityManager.createQuery("SELECT c FROM Carrito c WHERE c.usuario.id = :idUsuario", Carrito.class)
                .setParameter("idUsuario", idUsuario)
                .getSingleResult();

        List<Map<String, Object>> res = null;
        if (c != null){
            Query query = entityManager.createNativeQuery(
                    ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO_JSON
            );

            query.setParameter("carrito_id", c.getId());

            //procesamos la query
            NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setTupleTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            res = nativeQuery.getResultList();
        }

        return res;
    }




}
