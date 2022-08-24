package com.gmarquezp.web.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * AOP (Aspect Oriented Programming)
 *
 * - Util para invocar acciones transversales en varias clases, sin implementar el codigo en multiples partes
 * Encapsulan la logica de la funcionalidad trasnversal
 * */
@Component
@Aspect
public class EjemploAop {

    private static final Logger log = LoggerFactory.getLogger(EjemploAop.class);

    @Before("execution(* com.gmarquezp.web.app.aop.TargetObject.saludando())")
    // @Before("execution(* com.gmarquezp.web.app.aop.TargetObject.*(..))") // Escucahara la accion en todos los metodos de la clase
    // @Before("within(com.gmarquezp.web.app.aop.*)") // Escucahara la accion en todo el directorio
    // within(com.devs4j.spring.services..*) Se aplia a los metodos del paquete y subpaquetes
    // within(com.devs4j.spring.services.HelloWorld) // se aplica a los metodos de la clase

    // JoinPoint // Permite acceder informacion del metodo invocado
    public void before(JoinPoint joinPoint) {
        log.info("Antes de ejecutar la accion");
        log.info("Metodo invocado: {}", joinPoint.getSignature().getName());
        log.info("Argumentos: {}", joinPoint.getArgs());
        log.info("Object types: {}", joinPoint.getSignature().getDeclaringType());
    }


    @After("execution(* com.gmarquezp.web.app.aop.TargetObject.saludando())")
    public void after() {
        log.info("Despues de ejecutar la accion");
    }

    // Se ejecutara en los metodos o clases segun sea que tenga aplicada la anotacion
    @Before("@annotation(com.gmarquezp.web.app.aop.AnotacionEjemplo)")
    public void beforeAnnotation() {
        log.info("Antes de ejecutar la accion con anotacion");
    }
}

