package com.gmarquezp.web.app.autowire_listas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circulo implements Figura {

    @Value("${circulo.area}") // Inyectando el valor del archivo de propiedades
    private double radio;

    @Override
    public double calcularArea() {
        return this.radio;
    }
}
