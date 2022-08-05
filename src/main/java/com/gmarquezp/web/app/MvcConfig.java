package com.gmarquezp.web.app;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.gmarquezp.web.app.interceptors.TiempoTranscurridoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Necesaria para que al escanear las clases se ejecute y configure el contexto de spring
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("tiempoTranscurridoInterceptor")
    private HandlerInterceptor tiempoTranscurridoInterceptor;

    @Autowired
    @Qualifier("horarioInterceptor")
    private HandlerInterceptor horarioInterceptor;

    @Value("${app.uploads.path}")
    private String uploadsPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Registrando el interceptor
        // De esta forma se aplicara a todas las rutas, incluyendo archivos estaticos
        // registry.addInterceptor(this.tiempoTranscurridoInterceptor);

        // aplicara solo a todas las rutas que empicen por /formularios
        registry.addInterceptor(this.tiempoTranscurridoInterceptor).addPathPatterns("/formularios/**");


        registry.addInterceptor(this.horarioInterceptor).addPathPatterns("/horario-clientes");
        //registry.addInterceptor(this.horarioInterceptor).excludePathPatterns("/horario-clientes/cerrado");
    }


    // Configuracion de los recursos estaticos
    // por default el path es: src/main/resources/static/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/logos/**").addResourceLocations("file:/uploads/"); // Linux
        registry.addResourceHandler("/logos/**")
                .addResourceLocations("file:" + this.uploadsPath); // Windows
    }
}
