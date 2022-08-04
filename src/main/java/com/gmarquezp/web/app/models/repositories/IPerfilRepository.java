package com.gmarquezp.web.app.models.repositories;

import com.gmarquezp.web.app.models.relaciones.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
}
