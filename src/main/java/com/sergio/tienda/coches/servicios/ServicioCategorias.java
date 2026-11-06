package com.sergio.tienda.coches.servicios;

import com.sergio.tienda.coches.model.Categoria;
import com.sergio.tienda.coches.model.Coche;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicioCategorias {

    List<Categoria> obtenerCategorias();

    Categoria obtenerCategoriaPorId(Long id);

    void guardarCambiosCategoria(Categoria categoriaEditar) throws Exception;

    void borrarCategoria(long id) throws Exception;

    void registrarCategoria(Categoria c);



}
