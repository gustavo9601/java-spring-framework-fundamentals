package com.gmarquezp.web.app;


import com.gmarquezp.web.app.autowire_listas.Cuadrado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/*
* Anotando la clase e implementando la interfaz BeanPostProcessor
*
* Permite ejecutar eventos al instanciar un bean
* */
@Component
public class LifeCycleBean implements BeanPostProcessor {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);


    // Se ejecutara antes de inyectar el bean
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof Cuadrado) {
            log.info("Bean {} antes de inicializar", beanName);
        }

        return bean;
    }


    // Se ejecutara despues de inyectar el bean
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Cuadrado) {
            log.info("Bean {} inyectado", beanName);
        }
        return bean;
    }
}
