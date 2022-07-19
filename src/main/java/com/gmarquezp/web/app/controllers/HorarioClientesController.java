package com.gmarquezp.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/horario-clientes")
public class HorarioClientesController {

    @GetMapping({"", "/", "/index" })
    public String index(Model model) {
        model.addAttribute("titulo", "Horario Clientes");
        return "/horario_clientes/index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model){
        model.addAttribute("mensaje", "Horario Cerrado");
        return "/horario_clientes/cerrado";
    }

}
