package com.gmarquezp.web.app.models.relaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private Integer estatus;
    @Column(name = "fecharegistro")
    private Date fechaRegistro;

    // FetchType.EAGER // se cargara automaticamente la relacion
    @ManyToMany(fetch = FetchType.EAGER) // Un usuario tiene muchos perfiles, y un perfil tiene muchos usuarios
    // Configurara la tabla intermedia entre usuarios y perfiles
    @JoinTable(name = "usuarios_perfiles", // nombre de tabla
            joinColumns = @JoinColumn(name = "idusuario"), // Llave de la tabla usuarios
            inverseJoinColumns = @JoinColumn(name = "idperfil")) // Llave de la tabla perfiles
    private List<Perfil> perfiles;

    public UsuarioP() {
        this.perfiles = new ArrayList<>();
    }

    public UsuarioP agregarPerfil(Perfil perfil) {
        this.perfiles.add(perfil);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public String toString() {
        return "UsuarioP{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", estatus=" + estatus +
                ", fechaRegistro=" + fechaRegistro +
                ", perfiles=" + perfiles +
                '}';
    }
}
