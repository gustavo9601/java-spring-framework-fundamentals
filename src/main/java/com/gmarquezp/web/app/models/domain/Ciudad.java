package com.gmarquezp.web.app.models.domain;

import javax.validation.constraints.NotNull;

public class Ciudad {

    @NotNull
    private Long id;
    private String codigo;
    private String nombre;

    public Ciudad() {
    }

    public Ciudad(Long id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
