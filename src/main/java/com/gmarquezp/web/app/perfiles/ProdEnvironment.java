package com.gmarquezp.web.app.perfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/*
* Perfile
* -> Mediante una condicion indica que bean debe registrar o no
* */
@Service
@Profile("prod") // Especifica que se activara si se define ese perfil
public class ProdEnvironment implements IEnvironmentService {


    public String getEnvironment(){
        return "prod";
    }

}
