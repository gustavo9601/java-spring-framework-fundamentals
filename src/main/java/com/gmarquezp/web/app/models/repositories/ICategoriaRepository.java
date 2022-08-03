package com.gmarquezp.web.app.models.repositories;


import com.gmarquezp.web.app.models.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
// Repository
// CrudRepository
// JpaRepository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
