package com.gmarquezp.web.app.autowire_listas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cuadrado implements Figura {

    // :valor_default
    @Value("${cuadrado.area_default:1}") // Inyectando el valor del archivo de propiedades
    private double radio;
    @Override
    public double calcularArea() {
        return this.radio;
    }
}
