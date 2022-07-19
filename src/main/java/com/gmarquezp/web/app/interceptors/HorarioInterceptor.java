package com.gmarquezp.web.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer horarioApertura;

    @Value("${config.horario.cierre}")
    private Integer horarioCierre;

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour();

        logger.info("Hora local time: " + horaActual);

        if (horaActual >= this.horarioApertura && horaActual <= this.horarioCierre) {
            request.setAttribute("mensaje", "Horario abierto");
            return true;
        }

        logger.info("No se puede acceder a la aplicacion, llegaste por fuera del horario");

        // Redireccionando a otra ruta
        response.sendRedirect(request.getContextPath().concat("/horario-clientes/cerrado"));

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String mensaje = request.getAttribute("mensaje").toString();
        // Enviandolo a la vista
        modelAndView.addObject("mensaje", mensaje);
    }
}
