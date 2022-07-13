package com.gmarquezp.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formularios")
public class FormController {

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("titulo", "Formularios Index");
        return "formularios/index";
    }

    @PostMapping("/")
    public String store(Model model) {
        model.addAttribute("titulo", "Formularios Post");
        return "formularios/resultado";

    }
}
