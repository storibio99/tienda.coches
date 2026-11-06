package com.sergio.tienda.coches.responseData;

import java.util.List;
import java.util.Map;

//esta es la informacion que recibira el usuario para confirmar el pedido
public class ResumenPedido {

    //datos paso 1:
    private String nombreCompleto;
    private String direccion;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private String ciudad;

    //datos paso 2:
    private String titularTarjeta;
    private String numeroTarjeta;
    private String tipoTarjeta;
    private String fechaCaducidad;
    private String cvv;

    //se pide en paso 3 (NUEVO):
    private String tipoEntrega;                 // Ej: "Recogida en concesionario" o "Entrega a domicilio"
    private String concesionarioSeleccionado;   // Ej: "Concesionario Central Madrid"
    private Boolean requiereMatriculacion;      // Si solicita gestión de matriculación
    private String fechaPreferidaEntrega;       // Fecha estimada para recibir el coche
    //fin paso 3

    private List<Map<String,Object>> coches;

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

    public List<Map<String, Object>> getCoches() {
        return coches;
    }

    public void setCoches(List<Map<String, Object>> coches) {
        this.coches = coches;
    }
}
