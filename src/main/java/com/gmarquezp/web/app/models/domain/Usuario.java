package com.gmarquezp.web.app.models.domain;


import javax.validation.constraints.NotEmpty;

public class Usuario {

    @NotEmpty // Valida que no este vacio y tenga algo de texto
    private String nombre;
    @NotEmpty
    private String contrasena;
    @NotEmpty
    private String email;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, String email) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
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
}
