package com.gmarquezp.web.app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/query-params")
public class QueryParamsController {


    @GetMapping("/string")
    /*
      url?param
    * @RequestParam() String nombreComoSeEnviaElParametro
    * @RequestParam(name="nombreComoSeEnviaElParametro") String texto
    * @RequestParam(name="nombreComoSeEnviaElParametro", required=false) String texto // si no se envia el parametro, se pone null
    * */
    public String param(@RequestParam(required = false) String texto, Model model) {
        model.addAttribute("textoEnviadoPorParametro", texto);
        return "params/ver";
    }


    @GetMapping("/mix-params")
    // Sobrescritorua de metodo pasando difetenres parametros
    public String param(@RequestParam(required = false) String texto, @RequestParam(name = "edad") Integer edad, Model model) {
        model.addAttribute("textoEnviadoPorParametro", texto);
        model.addAttribute("mensaje", ("Texto:\t" + texto + "\t|\tEdad:\t" + edad));
        return "params/ver";
    }


    @GetMapping("/mix-params-request")
    // Sobrescritorua de metodo pasando difetenres parametros
    public String param(HttpServletRequest httpServletRequest, Model model) {
        // Obtenemos los parametros sin especificar por parametro, sino directamente en el request
        String texto = httpServletRequest.getParameter("texto");
        Integer edad;
        try {
            edad = Integer.parseInt(httpServletRequest.getParameter("edad"));
        } catch (NumberFormatException e) {
            edad = 0;
        }

        model.addAttribute("textoEnviadoPorParametro", httpServletRequest.getAttribute("texto"));
        model.addAttribute("mensaje", ("Texto:\t" + texto + "\t|\tEdad:\t" + edad));
        return "params/ver";
    }


    @GetMapping("")
    public String index() {
        return "params/index";
    }

}
