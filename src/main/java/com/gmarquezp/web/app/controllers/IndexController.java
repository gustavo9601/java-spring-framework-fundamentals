package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")  // Englobando el path, para las demas urls
public class IndexController {

    // @RequestMapping(value = "/index", method = RequestMethod.GET) == @GetMapping("/index")
    @GetMapping({"/index", "/", "/home", ""}) //   @GetMapping("/index"), recibe un array de rutas si es mas de una
    public String index(Model model) { // Model || ModelMap || Map<Type, Type>

        // Añadiendo variables para enviar a la vista
        // addAttribute(nombre, valor)
        model.addAttribute("title", "Home Spring Framework");

        // Retornamos un string con el nombre de la vista
        return "index";
    }

    @GetMapping("/perfil")
    public String usuario(Model model) {
        Usuario usuario = new Usuario( "Juan", "Perez", "juan@a.com", "123456");
        model.addAttribute("title", "Usuario Spring Framework");
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

}
