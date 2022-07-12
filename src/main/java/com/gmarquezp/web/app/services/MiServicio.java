package com.gmarquezp.web.app.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component // Spring will automatically scan this class and register it as a bean
@Service("miServicio") // Igual que un componente, pero aporta semantica y logica de negocio
public class MiServicio implements IServicio {
    public String operacion() {
        return "Ejecutando un proceso importante";
    }
}

