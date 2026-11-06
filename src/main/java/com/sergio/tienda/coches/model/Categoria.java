package com.sergio.tienda.coches.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {

    @Size(min = 2, max = 50, message = "Marca debe tener entre 2 y 50 caracteres")
    @NotEmpty(message = "Debes insertar una marca")
    @NotNull(message = "Debes indicar una marca")
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Coche> coches = new ArrayList<Coche>();

    @Id
    @GeneratedValue
    private int id;

    //FOTO PORTADA
    @Transient
    private MultipartFile fotoSubidaCategoria;
    @Lob
    private byte[] imagenCategoria;

    public MultipartFile getFotoSubidaCategoria() {
        return fotoSubidaCategoria;
    }

    public void setFotoSubidaCategoria(MultipartFile fotoSubidaCategoria) {
        this.fotoSubidaCategoria = fotoSubidaCategoria;
    }

    public byte[] getImagenCategoria() {
        return imagenCategoria;
    }

    public void setImagenCategoria(byte[] imagenCategoria) {
        this.imagenCategoria = imagenCategoria;
    }

    public Categoria() {
    }


    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
