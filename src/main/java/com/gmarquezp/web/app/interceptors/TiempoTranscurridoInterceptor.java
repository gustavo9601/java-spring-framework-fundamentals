package com.gmarquezp.web.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*;
import java.util.Random;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    // Usando la implementacion de Logger de SLF4J
    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("Ejecutandose Pre-handle");
        LocalDateTime fecha = LocalDateTime.now();
        logger.info("Fecha y hora inicial=\t" + fecha);

        long tiempoInicio = System.currentTimeMillis();
        logger.info("tiempoInicio = " + tiempoInicio + " milisegundos");

        Integer numeroRandom = new Random().nextInt(500); // Genera un numero aleatorio entre 0 y 500
        Thread.sleep(numeroRandom); // Sleep

        request.setAttribute("tiempoInicio", tiempoInicio); // guardandolo en el request

        return true; // Debe retornar un booleano para que continue con la ejecucion de la app
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        long tiempoFin = System.currentTimeMillis();

        LocalDateTime fecha = LocalDateTime.ofInstant(Instant.ofEpochMilli(tiempoFin), ZonedDateTime.now().getZone());
        logger.info("Fecha y hora final=\t" + fecha);

        long tiempoTranscurrido = tiempoFin - tiempoInicio;


        logger.info("Ejecutandose postHandle");
        logger.info("tiempoTranscurrido=\t" + tiempoTranscurrido + " milisegundos");

        if(modelAndView != null) {
            // Pasando a la vista
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }

    }
}
