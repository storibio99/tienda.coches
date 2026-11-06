package com.sergio.tienda.coches.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Coche {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 25, message = "Marca debe tener entre 2 y 25 caracteres")
    @NotEmpty(message = "Debes insertar una marca")
    @NotNull(message = "Debes indicar una marca")
    @Column(name = "marca")
    private String marca;

    @Size(min = 2, max = 50, message = "Modelo debe tener entre 2 y 50 caracteres")
    @NotEmpty(message = "Debes insertar un modelo")
    @NotNull(message = "Debes indicar un modelo")
    private String modelo;

    @Size(min = 6, max = 8, message = "La matricula debe tener 6 o 7 caracteres")
    @NotEmpty(message = "Debes insertar una matricula")
    @NotNull(message = "Debes indicar una matricula")
    private String matricula;

    @Size(min = 3, max = 10, message = "El tipo debe tener entre 3 y 10 caracteres")
    @NotEmpty(message = "Debes insertar un tipo")
    @NotNull(message = "Debes indicar un tipo")
    private String tipo; //electrico, hibrido, gasolina, diesel...

    @Min(value = 3, message = "Kilometros minimos: 3")
    @Max(value = 9999999, message = "Kilometros máximos: 9.999.999")
    private int kilometros;

    @Min(value = 1900, message = "Año minimo: 1900")
    @Max(value = 2027, message = "Año máximos: 2027")
    private int año;

    @Min(value = 5, message = "Potencia minimo: 5")
    @Max(value = 10000, message = "Potencia máximos: 10.000")
    private int cv;
    
    @NotNull(message = "Debes indicar si es de serie")
    private boolean stock;

    @Min(value = 10, message = "Precio minimo: 10")
    @Max(value = 999999999, message = "Precio máximos: 999.999.999")
    @NotNull(message = "Debes indicar un precio")
    private double precio;

    private String sku; //stock keeping unit //numero de control de producto

    //FOTO PORTADA
    @Transient
    private MultipartFile fotoSubida;
    @Lob
    private byte[] imagenPortada;

    //2º FOTO
    @Transient
    private MultipartFile fotoSubida2;
    @Lob
    private byte[] imagen2;


    //3º FOTO
    @Transient
    private MultipartFile fotoSubida3;
    @Lob
    private byte[] imagen3;


    //4º FOTO
    @Transient
    private MultipartFile fotoSubida4;
    @Lob
    private byte[] imagen4;


    //5º FOTO
    @Transient
    private MultipartFile fotoSubida5;
    @Lob
    private byte[] imagen5;

    //En asociaciones podemos especificar dos opciones para fetch(atrapar)
    //LAZY (vago) - Cuando se pida un coche no se va a pedir ningun dato de categoria a la base de datos
    //EAGER (ansioso) - Cuando se pide un coche, por defecto se pide a la base de datos la info de su categoria
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Categoria categoria;




    //para gestionar la id del form y para otros usos
    @Transient
    private int idCategoria;


    
    

    public Coche() {
		super();
	}

    public Coche(String marca, String modelo, String matricula, String tipo, int kilometros, int año, int cv, boolean stock, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.kilometros = kilometros;
        this.año = año;
        this.cv = cv;
        this.stock = stock;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    public MultipartFile getFotoSubida() {
        return fotoSubida;
    }

    public void setFotoSubida(MultipartFile fotoSubida) {
        this.fotoSubida = fotoSubida;
    }

    public byte[] getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(byte[] imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public MultipartFile getFotoSubida2() {
        return fotoSubida2;
    }

    public void setFotoSubida2(MultipartFile fotoSubida2) {
        this.fotoSubida2 = fotoSubida2;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public MultipartFile getFotoSubida3() {
        return fotoSubida3;
    }

    public void setFotoSubida3(MultipartFile fotoSubida3) {
        this.fotoSubida3 = fotoSubida3;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public MultipartFile getFotoSubida4() {
        return fotoSubida4;
    }

    public void setFotoSubida4(MultipartFile fotoSubida4) {
        this.fotoSubida4 = fotoSubida4;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public MultipartFile getFotoSubida5() {
        return fotoSubida5;
    }

    public void setFotoSubida5(MultipartFile fotoSubida5) {
        this.fotoSubida5 = fotoSubida5;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Coche{" +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", tipo='" + tipo + '\'' +
                ", kilometros=" + kilometros +
                ", año=" + año +
                ", cv=" + cv +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}

