package com.gmarquezp.web.app.models.repositories;

import com.gmarquezp.web.app.models.domain.Usuario;
import com.gmarquezp.web.app.models.relaciones.UsuarioP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioP, Integer> {

}
