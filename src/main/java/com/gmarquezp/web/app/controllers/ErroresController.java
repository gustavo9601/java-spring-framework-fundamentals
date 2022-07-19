package com.gmarquezp.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/errores")
public class ErroresController {

    @GetMapping("/")
    public String index(Model model) {
        return "/errores/index";
    }

}
