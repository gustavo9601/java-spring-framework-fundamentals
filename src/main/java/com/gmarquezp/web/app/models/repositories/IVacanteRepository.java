package com.gmarquezp.web.app.models.repositories;

import com.gmarquezp.web.app.models.relaciones.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVacanteRepository extends JpaRepository<Vacante, Integer> {

    /*
     * Query Methods
     * */
    List<Vacante> findByEstatus(String estatus);

    // Los parametros de la funcion deben concordar con la posicion en el nombre de la funcion
    List<Vacante> findByEstatusAndDestacadoOrderByIdDesc(String estatus, Integer destacado);

    // filtro por rango de salario
    List<Vacante> findBySalarioBetweenAnd(double min, double max);

    // filtro por varios estatus
    List<Vacante> findByEstatusIn(List<String> estatus);
}
