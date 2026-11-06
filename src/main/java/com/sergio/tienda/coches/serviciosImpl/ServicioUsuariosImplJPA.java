package com.sergio.tienda.coches.serviciosImpl;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Usuario;
import com.sergio.tienda.coches.servicios.ServicioCategorias;
import com.sergio.tienda.coches.servicios.ServicioUsuarios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ServicioUsuariosImplJPA implements ServicioUsuarios {

    //asi se pide el entity manager
    //que nos vale para comunicarnos con BD facilmente
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Usuario> obtenerUsuarios() {

        return entityManager.createQuery("select u from Usuario u").getResultList();
    }



    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return entityManager.find(Usuario.class, id);
    }



    @Override
    public void guardarCambiosUsuario(Usuario usuarioEditar) throws Exception {

        Usuario original = entityManager.find(Usuario.class, usuarioEditar.getId());

        //PARA FUTURA FOTO
        //if (!hasFile(cocheEditar.getFotoSubida())) {
        //    cocheEditar.setImagenPortada(original.getImagenPortada());
        //} else{
        //    cocheEditar.setImagenPortada(cocheEditar.getFotoSubida().getBytes());
        //}

        entityManager.merge(usuarioEditar);
    }// END GUARDAR_CAMBIOS_USUARIO




    @Override
    @Transactional
    public void borrarUsuario(long id) throws Exception {
        Usuario usuarioAborrar = entityManager.find(Usuario.class, id);

        if (usuarioAborrar != null) {
            // 1. Borramos el carrito primero si existe
            if (usuarioAborrar.getCarrito() != null) {
                entityManager.remove(usuarioAborrar.getCarrito());
            }

            // 2. Borramos el usuario
            entityManager.remove(usuarioAborrar);
        } else {
            throw new Exception("id no encontrada");
        }
    }

    @Override
    public void registrarUsuario(Usuario u) {

        entityManager.persist(u);
    }




    @Override
    public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
        Query query = entityManager.createQuery("select u from Usuario u where u.email = :email and u.pass = :pass");
        //la anterior consulta de jpa seria como la sql:
        //SELECT * FROM usuarios WHERE email = ? AND pass = ?
        query.setParameter("email", email);//asi asigno el :email
        query.setParameter("pass", pass);//asi asigno el :pass
        List<Usuario> res = query.getResultList();

        if (res.size() == 0){
            return null;
        }
        return res.get(0);
    }// END OBTENER USUARIOS POR EMAIL Y PASS


}
