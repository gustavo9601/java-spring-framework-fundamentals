package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.models.domain.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private Factura factura;

    @GetMapping({"/ver", ""})
    public String ver(Model model) {
        model.addAttribute("titulo", "Sistema de facturas");
        model.addAttribute("factura", this.factura);
        return "facturas/ver";
    }


}
