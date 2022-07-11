package com.gmarquezp.web.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/path-variable-params")
public class PathVariableParamsController {


    /*
    * Atributos en el application.properties
    * @Value("${llave_atributo}") // buscara la llame en el application.properties
    * */
    @Value("${texto.index.controller}")
    private String texto;
    @Value("${texto.index.controller.perfil}")
    private String textoPerfil;
    // Este valor se encuentra en otro archivo de properties personalizado
    @Value("${llave.mensaje.pruebas}")
    private String mensajePruebas;

    @GetMapping("")
    public String index() {
        return "path-variable/index";
    }

    @GetMapping("/string/{texto}") // {nombreVariable}
    /*
    * @PathVariable String texto
    * @PathVariable(name='nombreVariable') String texto2
    * */
    public String variables(@PathVariable String texto, Model model) {
        model.addAttribute("texto", texto);
        return "path-variable/ver";
    }


    @GetMapping("/string/{texto}/{edad}") // {nombreVariable}
    /*
     * @PathVariable String texto
     * @PathVariable(name='nombreVariable') String texto2
     * */
    public String variables(@PathVariable String texto, @PathVariable Integer edad,  Model model) {
        model.addAttribute("texto", texto);
        model.addAttribute("edad", edad);
        return "path-variable/ver";
    }
}
