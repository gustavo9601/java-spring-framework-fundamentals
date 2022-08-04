package com.gmarquezp.web.app.models.repositories;


import com.gmarquezp.web.app.models.relaciones.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository
// CrudRepository
// JpaRepository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
