package com.gmarquezp.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redireccion")
public class RedireccionController {

    @GetMapping("")
    public String index(Model model) {
        // Redireccionar a otra pagina solo anteponiendo el redirect (puede ser url externa tambien)
        model.addAttribute("eventoEnviado", "Redireccion");
        return "redirect:/redireccion/home";
    }

    @GetMapping("/home/forward")
    public String indexForward(Model model) {
        // Reenvia el request hacia la otra url, conservando el request inicial sin reiniciarlo como si ocurre con redirect
        model.addAttribute("eventoEnviado", "Forward");
        return "forward:/redireccion/home";
    }

    @GetMapping("/home")
    public String home() {
        return "redireccion/home";
    }

}
