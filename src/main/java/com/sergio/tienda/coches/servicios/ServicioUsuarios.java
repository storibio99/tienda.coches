package com.sergio.tienda.coches.servicios;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Usuario;

import java.util.List;

public interface ServicioUsuarios {

    List<Usuario> obtenerUsuarios();

    Usuario obtenerUsuarioPorId(Long id);

    void guardarCambiosUsuario(Usuario usuarioEditar) throws Exception;

    void borrarUsuario(long id) throws Exception;

    void registrarUsuario(Usuario u);

    Usuario obtenerUsuarioPorEmailYpass(String email, String pass);



}
