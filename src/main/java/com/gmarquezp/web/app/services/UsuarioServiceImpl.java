package com.gmarquezp.web.app.services;

import com.gmarquezp.web.app.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    List<Usuario> usuarios = List.of(
            new Usuario("Juan", "juan@a.com", "123456", "1"),
            new Usuario("Meliza", "meliza@a.com", "123456", "2")
    );

    @Override
    public List<Usuario> listar() {
        return this.usuarios;
    }

    @Override
    public Optional<Usuario> listarPorIdentificador(String id) {
        return this.listar()
                .stream()
                .filter(usuario -> usuario.getIdentificador().equals(id))
                .findFirst();
        // .orElse(null);
    }
}
