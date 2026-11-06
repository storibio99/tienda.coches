package com.sergio.tienda.coches.serviciosImpl;

import com.sergio.tienda.coches.constantesSQL.ConstantesSQL;
import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ServicioCochesImplJPA implements ServicioCoches {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void registrarCoche(Coche co) {

        try {
            co.setImagenPortada(getBytesOrNull(co.getFotoSubida()));
            co.setImagen2(getBytesOrNull(co.getFotoSubida2()));
            co.setImagen3(getBytesOrNull(co.getFotoSubida3()));
            co.setImagen4(getBytesOrNull(co.getFotoSubida4()));
            co.setImagen5(getBytesOrNull(co.getFotoSubida5()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //de la idCategoria recibida hay que asignar la categoria de la base de datos
        Categoria cat = entityManager.find(Categoria.class, co.getIdCategoria());
        co.setCategoria(cat);

        entityManager.persist(co);
    }




    @Override
    public List<Coche> obtenerCoches() {
        return entityManager.createQuery("select c from Coche c").getResultList();
    }

    @Override
    public List<Coche> obtenerCochePorMarca(String marca) {
        return entityManager.createQuery("select c from Coche c where lower(c.marca) like lower(:marca) ").setParameter("marca", "%" + marca + "%" ).getResultList();
    }

    @Override
    public List<Coche> obtenerCochePorMarcaYcomienzoYfin(String marca, int comienzo, int resultadosPorPagina) {
        return entityManager.createQuery("select c from Coche c where lower(c.marca) like lower(:marca) ").setParameter("marca", "%" + marca + "%" ).setFirstResult(comienzo).setMaxResults(resultadosPorPagina).getResultList();
    }


    @Override
    public Coche obtenerCochePorId(long id) {
        return entityManager.find(Coche.class, id);
    }//END OBTENERCOCHEPORID

    @Override
    public void borrarCoche(long id) throws Exception{

        Coche cocheAborrar = entityManager.find(Coche.class, id);

        if (cocheAborrar != null){
            entityManager.remove(cocheAborrar);
        }else {
           throw new Exception("id no encontrada");
        }
    }//END BORRAR_COCHE



    @Override
    public void guardarCambiosCoche(Coche cocheEditar) throws Exception {
        //de la id seleccionada en el desplegable de categorias tengo que asociar
        //la categoria correspondiente al coche
        Categoria cat = entityManager.find(Categoria.class, cocheEditar.getIdCategoria());
        cocheEditar.setCategoria(cat);


        Coche original = entityManager.find(Coche.class, cocheEditar.getId());

        if (!hasFile(cocheEditar.getFotoSubida())) {
            cocheEditar.setImagenPortada(original.getImagenPortada());
        } else{
            cocheEditar.setImagenPortada(cocheEditar.getFotoSubida().getBytes());
        }

        if (!hasFile(cocheEditar.getFotoSubida2())) {
            cocheEditar.setImagen2(original.getImagen2());
        } else{
            cocheEditar.setImagen2(cocheEditar.getFotoSubida2().getBytes());
        }

        if (!hasFile(cocheEditar.getFotoSubida3())) {
            cocheEditar.setImagen3(original.getImagen3());
        } else{
            cocheEditar.setImagen3(cocheEditar.getFotoSubida3().getBytes());
        }

        if (!hasFile(cocheEditar.getFotoSubida4())) {
            cocheEditar.setImagen4(original.getImagen4());
        } else{
            cocheEditar.setImagen4(cocheEditar.getFotoSubida4().getBytes());
        }

        if (!hasFile(cocheEditar.getFotoSubida5())) {
            cocheEditar.setImagen5(original.getImagen5());
        } else{
            cocheEditar.setImagen5(cocheEditar.getFotoSubida5().getBytes());
        }

        entityManager.merge(cocheEditar);

    } // END GUARDAR_CAMBIOS_COCHE

    @Override
    public List<Map<String, Object>> obtenerJSONcoches(String marca, int comienzo) {
        //con JPA hemos asocuado categoria a coche
        //problema: el json recibido en portada no es correcto
        //solucion: dejar claro en una consulta a base de datos que informacion
        //va a formar el json que necesitamos en portada

        //Map de String object es un conjunto de elementos
        //donde cada elemento tiene un String y un Object
        //un Object en java es cualquier topo de dato
        //Object es la clase padre de todas las clases en java
        //Map<String,Object> json = new HashMap<>();
        //json.put("titulo", 2);
        //json.put(2, 2);//da error porque lo primero no es un String
        //para tener el control total vamos a usar sql

        //asi preparo una sql para jpa

        Query consulta = entityManager.createNativeQuery(
                ConstantesSQL.SQL_OBTENER_COCHES_JSON
        );
        consulta.setParameter("marca", "%" + marca + "%");
        consulta.setParameter("comienzo", comienzo);
        //usar la consulta directamente es mucho mas lioso
        //que lo siguiente
        NativeQueryImpl nativeQuery = (NativeQueryImpl) consulta;
        nativeQuery.setTupleTransformer(AliasToEntityMapResultTransformer.INSTANCE);


        return nativeQuery.getResultList();
    }// END OBTENER_JSON_COCHES



    @Override
    public Map<String, Object> obtenerJSONCochePorId(Long id) {
        return (Map<String, Object>)((NativeQueryImpl)entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_COCHE_JSON)).setParameter("id",id).setTupleTransformer(AliasToEntityMapResultTransformer.INSTANCE).getResultList().get(0);
    }//END OBTENER_JSON_COCHE_POR_ID



    @Override
    public int obtenerTotalCoches(String marca) {
        // la sql es: "select count(id) from TABLA_VIDEOJUEGOS where TITULO_VIDEOJUEGO like :titulo"
        Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_COCHES);
        q.setParameter("marca", "%" + marca.toLowerCase() + "%");
        return Integer.parseInt( q.getSingleResult().toString() );
    }





    private static byte[] getBytesOrNull(MultipartFile file) throws IOException {
        return hasFile(file) ? file.getBytes() : null;
    }

    private static boolean hasFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }
}
