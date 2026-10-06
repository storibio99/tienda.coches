package com.sergio.tienda.coches.serviciosImpl;

import com.sergio.tienda.coches.model.Coche;
import com.sergio.tienda.coches.servicios.ServicioCoches;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class ServicioCocheImplJPA implements ServicioCoches {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void registrarCoche(Coche c) {

        try {
            c.setImagenPortada(getBytesOrNull(c.getFotoSubida()));
            c.setImagen2(getBytesOrNull(c.getFotoSubida2()));
            c.setImagen3(getBytesOrNull(c.getFotoSubida3()));
            c.setImagen4(getBytesOrNull(c.getFotoSubida4()));
            c.setImagen5(getBytesOrNull(c.getFotoSubida5()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        entityManager.persist(c);
    }




    @Override
    public List<Coche> obtenerCoches() {
        return entityManager.createQuery("select c from Coche c").getResultList();
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

    private static byte[] getBytesOrNull(MultipartFile file) throws IOException {
        return hasFile(file) ? file.getBytes() : null;
    }

    private static boolean hasFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }
}
