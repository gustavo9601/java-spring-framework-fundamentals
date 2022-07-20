package com.gmarquezp.web.app.services;

import com.gmarquezp.web.app.models.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listar();
    Optional<Usuario> listarPorIdentificador(String id);
}
