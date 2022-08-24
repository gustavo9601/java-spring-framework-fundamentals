package com.gmarquezp.web.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySources;

@Configuration
@PropertySource("classpath:areas.properties") // especifica el path del archivo a cargar como configuracion de atributos
public class ConfiguracionArchivosPropiedades {

    // Configurando el archivo se podra acceder a los atributos de la clase con Value("${propiedad})
    @Bean
    public PropertySourcesPlaceholderConfigurer loadProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}


