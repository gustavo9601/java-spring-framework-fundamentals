package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.models.domain.Ciudad;
import com.gmarquezp.web.app.models.domain.Usuario;
import com.gmarquezp.web.app.validators.UsuarioContrasenaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("usuario") // indica que se podra acceder a este objeto desde la sesion
@RequestMapping("/formularios")
public class FormController {


    @Autowired
    private UsuarioContrasenaValidator contrasenaValidator; // Inyectamos el validador de contrasena


    @InitBinder // Hace la inyeccion del validador de forma implicita cuando se use el @Valid
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(this.contrasenaValidator); // Inyectamos el validador de contrasena
    }

    @GetMapping("")
    public String form(Model model) {
        model.addAttribute("titulo", "Formularios Index");
        // Creamos el objeto vacio, para que no de error al momento de acceder por el Objeto desde la vista
        Usuario usuario = new Usuario();
        usuario.setValorSecreto("Valor ultra secreto");
        usuario.setEstaActivo(true);
        usuario.setRoles(List.of("ROLE_USER"));

        model.addAttribute("usuario", usuario);


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

        // Llamamos al validador personalizado de contrasena
        // this.contrasenaValidator.validate(usuario, result);

        if (result.hasErrors()) {
            System.out.println(" === Errores getAllErrors() == ");
            System.out.println(result.getAllErrors());

            Map<String, String> errores = new HashMap<>();
            System.out.println(" === Errores list == ");

            result.getFieldErrors().forEach(error -> {
                System.out.println(error.getCode() + "  |  " + error.getField() + ": " + error.getDefaultMessage());
                errores.put(error.getCode() + "." + error.getField(), error.getDefaultMessage());

            });

            System.out.println(" === Errores Map == ");
            System.out.println("errores = " + errores);

            model.addAttribute("errores", errores);

            return "formularios/index";

        }

        // Se redirecciona, para que al recrgar el navegador no se vuelva a enviar el request
        // En la session se guardo el usuario, para que se acceda desde el endpoint
        return "redirect:/formularios/ver";

    }

    @GetMapping("/ver")
    public String ver(
            @SessionAttribute(value = "usuario", required = false) Usuario usuario, // Obtenemos el usuario de la sesion
            Model model,
            SessionStatus status // Indica que se termino de procesar el formulario
    ) {


        System.out.println("usuario  a enviar a la vista=\t" + usuario);
        if (usuario == null){ // Para reenviar si se accede directamente al path sin que el usuario exista en la session
            return "redirect:/formularios";
        }


        model.addAttribute("usuario", usuario);

        status.setComplete();

        return "formularios/resultado";
    }

    // El retorno de la funcion, se podra acceder en la vista con el nombre que se pase por parametro
    @ModelAttribute("generos")
    public List<String> generos() {
        return List.of("Masculino", "Femenino", "No sabe no responde");
    }

    @ModelAttribute("paises")
    public Map<String, String> paises() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("FR", "Francia");
        paises.put("UK", "Reino Unido");
        return paises;
    }

    @ModelAttribute("ciudades")
    public List<Ciudad> ciudades() {
        return List.of(
                new Ciudad(1L, "Bogota", "BOG"),
                new Ciudad(2L, "Medellin", "MED"),
                new Ciudad(3L, "Cali", "CAL")
        );
    }

    @ModelAttribute("rolesString")
    public List<String> roles() {
        return List.of(
                "ROLE_ADMIN", "ROLE_USER"
        );
    }


}
