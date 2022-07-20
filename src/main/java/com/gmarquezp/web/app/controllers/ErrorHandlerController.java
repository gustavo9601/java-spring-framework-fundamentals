package com.gmarquezp.web.app.controllers;

import com.gmarquezp.web.app.exeptions.ExceptionUsuarioNoEncontrado;
import com.gmarquezp.web.app.interceptors.TiempoTranscurridoInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Anotacion que permite que el controlador escuche todas las exepciones
@ControllerAdvice
public class ErrorHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);


    // Especificando en el hadler que excepcion va a manejar, puede ser N tipos de excepcion en un arreglo {}
    @ExceptionHandler(ArithmeticException.class)
    public String arimeticaError(Exception exception, Model model) {
        model.addAttribute("titulo", "Error Arimetica");
        model.addAttribute("mensajeError", exception.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        logger.error("Error Arimetica: " + exception.getMessage());

        return "/error/error-handler";
    }


    @ExceptionHandler(NumberFormatException.class)
    public String parseError(Exception exception, Model model) {
        model.addAttribute("titulo", "Error Parse");
        model.addAttribute("mensajeError", exception.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        logger.error("Error Parse: " + exception.getMessage());

        return "/error/error-handler";
    }

    @ExceptionHandler(ExceptionUsuarioNoEncontrado.class)
    public String errorExepcionPersonalizada(Exception exception, Model model) {
        model.addAttribute("titulo", "Error Exepcion Personalizada Usuario no encontrada");
        model.addAttribute("mensajeError", exception.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        logger.error("Error Exepcion Personalizada Usuario no encontrada: " + exception.getMessage());

        return "/error/error-handler";
    }


}
