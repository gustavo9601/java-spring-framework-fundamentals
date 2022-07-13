package com.gmarquezp.web.app;

import com.gmarquezp.web.app.models.domain.ItemFactura;
import com.gmarquezp.web.app.models.domain.Producto;
import com.gmarquezp.web.app.services.IServiciosComplejos;
import com.gmarquezp.web.app.services.ServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

// Las clases configuration deben estar dentro del package principal
@Configuration
public class AppConfig {

    // @Bean // Spring will automatically scan this class and register it as a bean
    // Permite registrar un bean en el contexto de spring, sin usar una anotacion en la implementacion
    @Bean("miServicioComplejo")
    public IServiciosComplejos miServicioComplejo() {
        return new ServicioComplejo();
    }


    // Permitira inyectar este metodo cuando se invoque el auwtowired con retorno de tipo List<ItemFactura>
    // @Primary
    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems() {
        Producto product1 = new Producto("Producto 1", 100D);
        Producto product2 = new Producto("Producto 2", 10050D);

        ItemFactura item1 = new ItemFactura(product1, 2);
        ItemFactura item2 = new ItemFactura(product2, 1);

        return Arrays.asList(item1, item2);
    }


    @Bean("registrarItemsOficina")
    public List<ItemFactura> registrarItemsOficina() {
        Producto product1 = new Producto("Monitor LG", 100D);
        Producto product2 = new Producto("Monitor Asus", 10050D);
        Producto product3 = new Producto("Monitor Samsung", 100D);
        Producto product4 = new Producto("Monitor Dell", 10050D);

        ItemFactura item1 = new ItemFactura(product1, 2);
        ItemFactura item2 = new ItemFactura(product2, 1);
        ItemFactura item3 = new ItemFactura(product3, 10);
        ItemFactura item4 = new ItemFactura(product4, 10);

        return Arrays.asList(item1, item2, item4, item3);
    }
}
