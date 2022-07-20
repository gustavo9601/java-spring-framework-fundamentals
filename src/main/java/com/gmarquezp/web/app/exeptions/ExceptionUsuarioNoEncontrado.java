package com.gmarquezp.web.app.exeptions;

public class ExceptionUsuarioNoEncontrado extends RuntimeException {

    public ExceptionUsuarioNoEncontrado(String id) {
        super("Usuario con identificador " + id + " no encontrado");
    }

}
