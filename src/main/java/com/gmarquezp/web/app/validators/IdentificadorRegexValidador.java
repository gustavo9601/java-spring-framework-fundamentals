package com.gmarquezp.web.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator<Anotacion a enlazar, Tipo de dato con el cual se validara>
public class IdentificadorRegexValidador implements ConstraintValidator<IdentifcadorRegex, String> {
    @Override
    // Value => sera el valor que se va a validar
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Entra en la validacion =\t" + value.matches("[A-Za-z0-9]*"));
        return value.matches("[A-Za-z0-9]*");
    }
}
