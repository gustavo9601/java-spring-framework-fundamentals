package com.gmarquezp.web.app.services;

import org.springframework.context.annotation.Primary;


@Primary // Le da prioridad a esta implementacion para que use esta
public class MiServicio2 implements IServicio {
    public String operacion() {
        return "Ejecutando un proceso importante 2";
    }

}
