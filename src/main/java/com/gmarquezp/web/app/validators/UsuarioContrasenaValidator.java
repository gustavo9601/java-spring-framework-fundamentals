package com.gmarquezp.web.app.validators;

import com.gmarquezp.web.app.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioContrasenaValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // Si el objeto que se va a validar es del tipo Usuario, se devuelve true
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;

        /*if(usuario.getContrasena().isBlank()){
            // nombre del campo, mensaje de error en el messages.properties
            errors.rejectValue("contrasena", "NotEmpty.usuario.contrasena");
        }*/

        // errores, nombre del campo, mensaje de error en el messages.properties
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.contrasena");


        if(!usuario.getContrasena().matches("[A-Za-z0-9]*")){
            errors.rejectValue("contrasena", "pattern.usuario.contrasena");
        }
    }
}
