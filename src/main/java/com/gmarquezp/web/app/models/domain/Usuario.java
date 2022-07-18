package com.gmarquezp.web.app.models.domain;


import com.gmarquezp.web.app.validators.IdentifcadorRegex;
import com.gmarquezp.web.app.validators.Requerido;
import com.gmarquezp.web.app.validators.UsuarioContrasenaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Usuario {

    // @Pattern(regexp = "[A-Za-z0-9]*", message = "El identificador no puede contener caracteres especiales")
    @IdentifcadorRegex  //  Validador personalizado usando anotaciones
    @Size(min = 1, max = 3)
    private String identificador;

    /*
     * message ="" // espeifica el mensaje a retornar en la validacion
     * Para organizar se usa el archivo resources/messages.properties donde se puede se descaoplan los mensajes
     * */
    @NotEmpty(message = "El nombre es requerido y no puede ser vacio") // Valida que no este vacio y tenga algo de texto
    @Size(min = 3, max = 20) // Valida que tenga entre 3 y 20 caracteres
    private String nombre;
    @NotBlank  // Valida que no este vacio y tenga algo de texto exepto de espacios en blanco
    private String contrasena;
    @NotEmpty
    @Email
    private String email;

    @Min(18) // Valida que sea mayor a 18
    @Max(100) // Valida que sea menor a 100
    @NotNull // Validacion de nullo para tipos objeto y  != String
    private Integer edad;

    @Requerido // Validacion personalizada
    private String paisNacimiento;

    @NotNull
    @Past // La fecha debe ser pasada o igual a la actual
    // @Future // La fecha debe ser futura o igual a la actual
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato de fecha
    private Date fechaNacimiento;


    @NotEmpty
    private String genero;

    @Valid  // Indica que use las validaciones internas del objeto
    private Ciudad ciudad;

    @NotEmpty // Sirve para validar tambien listas
    private List<String> roles;

    // Podra ser opcional
    private Boolean estaActivo;

    @NotNull
    private String valorSecreto;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, String email) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Usuario(String nombre, String contrasena, String email, Integer edad, String identificador, String paisNacimiento) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.edad = edad;
        this.identificador = identificador;
        this.paisNacimiento = paisNacimiento;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public String getValorSecreto() {
        return valorSecreto;
    }

    public void setValorSecreto(String valorSecreto) {
        this.valorSecreto = valorSecreto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "identificador='" + identificador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", paisNacimiento='" + paisNacimiento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero='" + genero + '\'' +
                ", ciudad=" + ciudad +
                ", roles=" + roles +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
