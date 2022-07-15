package com.gmarquezp.web.app.models.domain;


import javax.validation.constraints.*;

public class Usuario {

    /*
    * message ="" // espeifica el mensaje a retornar en la validacion
    * Para organizar se usa el archivo resources/messages.properties donde se puede se descaoplan los mensajes
    * */
    @NotEmpty(message = "El nombre es requerido y no puede ser vacio") // Valida que no este vacio y tenga algo de texto
    @Size(min = 3, max = 20) // Valida que tenga entre 3 y 20 caracteres
    private String nombre;
    @NotEmpty
    private String contrasena;
    @NotEmpty
    @Email
    private String email;

    @Min(18) // Valida que sea mayor a 18
    @Max(100) // Valida que sea menor a 100
    private Integer edad;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, String email) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Usuario(String nombre, String contrasena, String email, Integer edad) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                '}';
    }
}
