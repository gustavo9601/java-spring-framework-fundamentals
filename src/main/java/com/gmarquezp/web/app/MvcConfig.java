package com.gmarquezp.web.app;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.gmarquezp.web.app.interceptors.TiempoTranscurridoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Necesaria para que al escanear las clases se ejecute y configure el contexto de spring
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("tiempoTranscurridoInterceptor")
    private HandlerInterceptor tiempoTranscurridoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Registrando el interceptor
        // De esta forma se aplicara a todas las rutas, incluyendo archivos estaticos
        registry.addInterceptor(this.tiempoTranscurridoInterceptor);
    }
}
