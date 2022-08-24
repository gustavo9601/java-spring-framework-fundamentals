package com.gmarquezp.web.app.perfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/*
 * Perfile
 * -> Mediante una condicion indica que bean debe registrar o no
 * -> En el archivo properties se puede definir el perfil a usar por default
 * */
@Service
@Profile(value = {"qa", "default"}) // Especifica que se activara si se define ese perfil
// default // si no existe la configuracion en el properties aplicara esta inyeccion del bean
public class QaEnvironment implements IEnvironmentService {


    public String getEnvironment() {
        return "qa";
    }

}
