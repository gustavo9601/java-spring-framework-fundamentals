package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/formularios")
public class FormController {

    @GetMapping("")
    public String form(Model model) {
        model.addAttribute("titulo", "Formularios Index");
        // Creamos el objeto vacio, para que no de error al momento de acceder por el Objeto desde la vista
        model.addAttribute("usuario", new Usuario());

        return "formularios/index";
    }

    @PostMapping("/form")
    // @RequestParam nameVariable || @RequestParam(name = "nombre") nameVariable
    // Permite especificar individualmente el nombre de la variable que se recibe en el request
    public String store(Model model,
                        @RequestParam String nombre,
                        @RequestParam String contrasena,
                        @RequestParam String email
    ) {

        Usuario usuario = new Usuario(nombre, contrasena, email);

        model.addAttribute("usuario", usuario);

        return "formularios/resultado";

    }

    @PostMapping("/form-automatico")
    // Pasando el POJO por parametro esperara exactamente las mismas variables y se llenaran automaticamente
    // Debe tener los getters and setters
    // @Valid // Indica que debe validar el objeto
    // @Valid @ModelAttribute("otro_nombre")// Le da otro nombre para acceder al atributo desde la vista
    public String store(@Valid Usuario usuario,
                        BindingResult result, // Contiene los errores de la validacion
                        Model model
    ) {

        if (result.hasErrors()) {
            System.out.println("Error no paso la validacion");
            System.out.println(" === Errores getAllErrors() == ");
            System.out.println(result.getAllErrors());

            Map<String, String> errores = new HashMap<>();
            System.out.println(" === Errores list == ");
            result.getFieldErrors().forEach(error -> {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
                errores.put(error.getField(), error.getDefaultMessage());
            });

            System.out.println(" === Errores Map == ");
            System.out.println("errores = " + errores);

            model.addAttribute("errores", errores);

            return "formularios/index";

        }

        model.addAttribute("usuario", usuario);

        return "formularios/resultado";

    }
}
