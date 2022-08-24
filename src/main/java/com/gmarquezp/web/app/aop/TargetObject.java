package com.gmarquezp.web.app.aop;

import org.springframework.stereotype.Component;

@Component
public class TargetObject {
    public void saludando() {
        System.out.println("Hola !!!!");
    }

    @AnotacionEjemplo
    public void despedida() {
        System.out.println("Adios !!!!");
    }
}
