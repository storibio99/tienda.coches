package com.sergio.tienda.coches.serviciosImpl;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCategorias;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ServicioCategoriasImplJPA implements ServicioCategorias {

    //asi se pide el entity manager
    //que nos vale para comunicarnos con BD facilmente
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Categoria> obtenerCategorias() {

        return entityManager.createQuery("select c from Categoria c").getResultList();
    }



    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return entityManager.find(Categoria.class, id);
    }



    @Override
    public void guardarCambiosCategoria(Categoria categoriaEditar) throws Exception {

        Categoria original = entityManager.find(Categoria.class, categoriaEditar.getId());

        if (!hasFile(categoriaEditar.getFotoSubidaCategoria())) {
            categoriaEditar.setImagenCategoria(original.getImagenCategoria());
        } else{
            categoriaEditar.setImagenCategoria(categoriaEditar.getFotoSubidaCategoria().getBytes());
        }

        entityManager.merge(categoriaEditar);
    }// END GUARDAR_CAMBIOS_CATEGORIA




    @Override
    public void borrarCategoria(long id) throws Exception {

        if (id == 1){
            throw new IllegalArgumentException("No borrar sin categoria");
        }

        Categoria categoriaAborrar = entityManager.find(Categoria.class, id);

        if (categoriaAborrar != null){

            List<Coche> coches = categoriaAborrar.getCoches();
            for (int i=0; i<coches.size();i++){
                coches.get(i).setCategoria(entityManager.find(Categoria.class, 1));
            }

            entityManager.remove(categoriaAborrar);
        }else {
            throw new Exception("id no encontrada");
        }

    }

    @Override
    public void registrarCategoria(Categoria c) {
        //PARA IMAGEN
        //try {
        //    c.setImagenPortada(getBytesOrNull(co.getFotoSubida()));
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        entityManager.persist(c);
    }



    private static boolean hasFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }


}
