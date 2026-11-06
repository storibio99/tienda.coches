package com.sergio.tienda.coches.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @NotBlank(message = "Debes insertar un nombre")
    @Size(min = 2, max = 25, message = "El nombre debe tener entre 2 y 25 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]*$",
            message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    @NotBlank(message = "Debes insertar los apellidos")
    @Size(min = 2, max = 60, message = "Los apellidos deben tener entre 2 y 60 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]*$",
            message = "Los apellidos solo pueden contener letras y espacios")
    private String apellidos;

    @NotBlank(message = "Debes insertar un email")
    @Size(min = 6, max = 40, message = "El email debe tener entre 6 y 40 caracteres")
    @Email(message = "El formato del email no es válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Debes insertar un número de teléfono")
    @Pattern(regexp = "^[6789]\\d{8}$",
            message = "El teléfono debe tener 9 dígitos y empezar por 6, 7, 8 o 9")
    private String numTelefono;

    @NotBlank(message = "Debes insertar una contraseña")
    @Size(min = 5, max = 30, message = "La contraseña debe tener entre 5 y 30 caracteres")
    private String pass;

    @NotBlank(message = "Debes insertar la fecha de nacimiento")
    private String fechaNacimiento;

    private String sku; //stock keeping unit //numero de control de producto

    @OneToOne
    private Carrito carrito;




    @Lob
    private byte[] avatar;


    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String email, String numTelefono, String pass, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.numTelefono = numTelefono;
        this.pass = pass;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSku() {
        return sku;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }



    public void setSku(String sku) {
        this.sku = sku;
    }


    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", numTelefono='" + numTelefono + '\'' +
                ", pass='" + pass + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
