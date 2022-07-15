package com.gmarquezp.web.app.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentificadorRegexValidador.class) // Enlazando con la clase que implementa la interfaz ConstraintValidator
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IdentifcadorRegex {

    /*
    * Parametros por default, que podra recibir la anotacion
    * */

    String message() default "Indetificador invalido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
