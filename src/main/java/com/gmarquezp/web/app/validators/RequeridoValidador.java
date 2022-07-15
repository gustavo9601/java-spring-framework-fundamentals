package com.gmarquezp.web.app.validators;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       // return value != null && !value.isEmpty() && !value.isBlank();
        //StringUtils // Helper de spring
        return StringUtils.hasText(value);
    }
}
