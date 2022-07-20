package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.exeptions.ExceptionUsuarioNoEncontrado;
import com.gmarquezp.web.app.interceptors.TiempoTranscurridoInterceptor;
import com.gmarquezp.web.app.models.domain.Usuario;
import com.gmarquezp.web.app.services.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/errores")
public class ErroresController {


    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/")
    public String index(Model model) {
        return "/errores/index";
    }

    @GetMapping("/error-arimetica")
    public String errorArimetica() {
        // La division sera controlada por el ErroHandlerController
        Integer divicion0 = 10 / 0;
        return "/error/index";

    }


    @GetMapping("/error-parse")
    public String errorParse() {
        Integer stringToInteger = Integer.parseInt("10True");
        return "/error/index";

    }


    @GetMapping("/error-exepcion-personalizada/{id}")
    public String errorExepcionPersonalizada(@PathVariable String id, Model model) {

        Usuario usuario = this.usuarioService.listarPorIdentificador(id)
                .orElseThrow(() -> new ExceptionUsuarioNoEncontrado(id)); // otra forma de llamar la exepcion

        // Lanzamos la exepcion personalizada, para que en el handler la analice de forma personalizada

        /*if (usuario.isEmpty()) {
            throw new ExceptionUsuarioNoEncontrado(id);
        }*/


        logger.info("Usuario encontrado: " + usuario.getNombre());

        return "/error/index";

    }

}
