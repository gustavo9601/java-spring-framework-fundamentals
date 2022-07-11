package com.gmarquezp.web.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        // classpath // retorna el path al /main/resources/
        @PropertySource("classpath:textos.pruebas.properties")
      //  @PropertySources.Source("classpath:application.yml")
})
public class TextosPruebasPropertiesConfig {
}
