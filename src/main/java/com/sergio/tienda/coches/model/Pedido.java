package com.sergio.tienda.coches.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Usuario.class, optional = false)
    private Usuario usuario;


    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ProductoPedido> productosPedido =
            new ArrayList<ProductoPedido>();


    private String estado;


    //se pide en paso 1:

    private String nombreCompleto;
    private String direccion;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private String ciudad;

    //fin paso 1


    //se pide en paso 2:

    private String titularTarjeta;
    private String numeroTarjeta;
    private String tipoTarjeta;
    private String fechaCaducidad;
    private String cvv;
    //fin paso 2



    //se pide en paso 3 (NUEVO):
    private String tipoEntrega;                 // Ej: "Recogida en concesionario" o "Entrega a domicilio"
    private String concesionarioSeleccionado;   // Ej: "Concesionario Central Madrid"
    private Boolean requiereMatriculacion;      // Si solicita gestión de matriculación
    private String fechaPreferidaEntrega;       // Fecha estimada para recibir el coche
    //fin paso 3


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ProductoPedido> getProductosPedido() {
        return productosPedido;
    }

    public void setProductosPedido(List<ProductoPedido> productosPedido) {
        this.productosPedido = productosPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTitularTarjeta() {
        return titularTarjeta;
    }

    public void setTitularTarjeta(String titularTarjeta) {
        this.titularTarjeta = titularTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getConcesionarioSeleccionado() {
        return concesionarioSeleccionado;
    }

    public void setConcesionarioSeleccionado(String concesionarioSeleccionado) {
        this.concesionarioSeleccionado = concesionarioSeleccionado;
    }

    public Boolean getRequiereMatriculacion() {
        return requiereMatriculacion;
    }

    public void setRequiereMatriculacion(Boolean requiereMatriculacion) {
        this.requiereMatriculacion = requiereMatriculacion;
    }

    public String getFechaPreferidaEntrega() {
        return fechaPreferidaEntrega;
    }

    public void setFechaPreferidaEntrega(String fechaPreferidaEntrega) {
        this.fechaPreferidaEntrega = fechaPreferidaEntrega;
    }
}



