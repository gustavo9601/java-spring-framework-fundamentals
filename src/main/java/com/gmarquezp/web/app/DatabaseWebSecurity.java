package com.gmarquezp.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


/*
 * Clase necesaria para que tome los usuarios desde la BD
 * */
@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // Configuracion base para autenticar y autorizar
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource) // Configurando la bd por default
                .usersByUsernameQuery("select username, password, enabled from users where username=?") // Consulta para obtener el usuario
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?"); // Consulta para obtener los authorities (roles)
    }


    // Configuracion base de urls que protegera y cuales no
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Los recursos estáticos no requieren autenticación
                .antMatchers(
                        "/bootstrap/**",
                        "/images/**",
                        "/tinymce/**",
                        "/logos/**"
                ).permitAll()
                // Las vistas públicas no requieren autenticación
                .antMatchers("/",
                        "/signup",
                        "/search",
                        "/vacantes/view/**",
                        "/**").permitAll()
                // Protegiendo por Roles los siguientes recursos
                .antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")

                // Todas las demás URLs de la Aplicación requieren autenticación
                .anyRequest().authenticated()
                // El formulario de Login no requiere autenticacion
                .and().formLogin().permitAll();
    }


    /*
    * // Creando un bean, para encriptar contraseñas
    * // SpringSecurity si detectara y esperar siempre recibir las contraseñas encriptadas
    * */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
