package com.gmarquezp.web.app.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequeridoValidador.class) // Enlazando con la clase que implementa la interfaz ConstraintValidator
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Requerido {
    /*
     * Parametros por default, que podra recibir la anotacion
     * */

    String message() default "Campo requerido omee";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
