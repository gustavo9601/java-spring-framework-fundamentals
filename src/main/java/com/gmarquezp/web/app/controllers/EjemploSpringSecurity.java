package com.gmarquezp.web.app.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping(value = "/ejemploSpringSecurity")
public class EjemploSpringSecurity {

    // Authentication authentication // retorna el usuario autenticado
    @GetMapping(value = "/")
    public String index(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            authorities.forEach(authority -> {
                System.out.println("Role=>\t" + authority.getAuthority());
            });
            System.out.println("username = " + username);

        }

        return "ejemploSpringSecurity";
    }

}
