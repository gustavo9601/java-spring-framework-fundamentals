package com.gmarquezp.web.app.models.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
@RequestScope // Tendra alcance dentro del contenedor de inyeccion por request
// @SessionScope // Tendra alcance dentro del contenedor de inyeccion por session
    /*
    * Si es de session requiere que se implemente la interfaz serializable, para que se serialice en la sesion
    * */
// @ApplicationScope // Tendra alcance dentro del contenedor de inyeccion por application, similar al por default
public class Factura {
    @Value("${factura.descripcion}")
    private String descripcion;
    @Autowired
    private Cliente cliente;

    @Autowired
    @Qualifier("itemsFactura")
    private List<ItemFactura> itemsFactura;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItemsFactura() {
        return itemsFactura;
    }

    public void setItemsFactura(List<ItemFactura> itemsFactura) {
        this.itemsFactura = itemsFactura;
    }


    /*
     * Ciclo de vida de un bean
     * */


    @PostConstruct //Se ejecuta despues de la ejecucion del constructor
    public void initializar() {
        System.out.println("PostConstruct Factura =)");
    }

    @PreDestroy //Se ejecuta antes de la destruccion del bean
    // Se ejecutara hasta cuando finalice la ejecucion del programa
    public void destruir() {
        System.out.println("PreDestroy Factura =(");
    }

}
