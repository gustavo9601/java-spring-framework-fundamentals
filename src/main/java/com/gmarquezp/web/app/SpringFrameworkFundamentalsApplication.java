package com.gmarquezp.web.app;

import com.gmarquezp.web.app.models.domain.Categoria;
import com.gmarquezp.web.app.models.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class SpringFrameworkFundamentalsApplication implements CommandLineRunner {


    @Autowired
    private ICategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkFundamentalsApplication.class, args);
    }

    /*
     * Se ejecutara cuando se inicialice la app
     * */
    @Override
    public void run(String... args) throws Exception {
        this.imprimirTitulos("Creando categoria");
        this.guardarCategoria();
        this.imprimirTitulos("Buscar por id");
        this.buscardPorId(1);
        this.imprimirTitulos("Buscar todos por id");
        this.buscarTodosdPorId(1);
        this.imprimirTitulos("Buscar todos");
        this.buscarTodos();
        this.imprimirTitulos("Buscar todos ordenados");
        this.buscarTodosOrdenados();
        this.imprimirTitulos("Buscar todos Paginados");
        this.buscarTodosPaginados();
        this.imprimirTitulos("Buscar todos Paginados & Ordenados");
        this.buscarTodosOrdenadosYPaginados();
        this.imprimirTitulos("Categoria por id existe");
        this.existePorId(1);
        this.imprimirTitulos("Actualizar categoria por id");
        this.actualizar(1);
        this.imprimirTitulos("Total categorias");
        System.out.println("Total=\t" + this.categoriaRepository.count());
        this.imprimirTitulos("Eliminar todos");
        this.elimiarTodos();
        // this.imprimirTitulos("Eliminando categoria por id");
        // this.eliminar(2);
        this.imprimirTitulos("Guardado masivo");
        this.guardarMasivo();
    }



    /*
     *
     * */

    private void guardarCategoria() {
// Se crea una categoria
        Categoria categoria = new Categoria();
        LocalDateTime hoy = LocalDateTime.now();
        categoria.setNombre("Desarrollo");
        categoria.setDescripcion("Desarrollo de software - " + hoy);
        this.categoriaRepository.save(categoria);
        System.out.println("Usuario creado=\t" + categoria);
    }


    private void buscardPorId(Integer id) {
        Optional<Categoria> categoriaPorId = this.categoriaRepository.findById(id);
        if (categoriaPorId.isPresent()) {
            System.out.println("Categoria encontrada=\t" + categoriaPorId.get());
        }
    }

    private void buscarTodosdPorId(Integer id) {
        Iterable<Categoria> categorias = this.categoriaRepository.findAllById(Arrays.asList(id));

        categorias.forEach(categoria -> {
            System.out.println("Categoria encontrada=\t" + categoria);
        });
    }

    private void buscarTodos() {
        List<Categoria> categorias = this.categoriaRepository.findAll();

        categorias.forEach(categoria -> {
            System.out.println("Categoria encontrada=\t" + categoria);
        });
    }

    private void buscarTodosOrdenados() {
        // Ordena por nombre
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        List<Categoria> categorias = this.categoriaRepository.findAll(sort);

        categorias.forEach(categoria -> {
            System.out.println("Categoria encontrada=\t" + categoria);
        });
    }

    private void buscarTodosPaginados() {
        // Obteniendo una paginacion
        // PageRequest.of(pagina_inicio, cantidad_registros);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Categoria> categorias = this.categoriaRepository.findAll(pageable);

        categorias.forEach(categoria -> {
            System.out.println("Categoria encontrada=\t" + categoria);
        });

        System.out.println("categorias.getContent()=\t" + categorias.getContent());
        System.out.println("categorias.getTotalElements()=\t" + categorias.getTotalElements());
        System.out.println("categorias.getTotalPages()=\t" + categorias.getTotalPages());

    }


    private void buscarTodosOrdenadosYPaginados() {

        Sort sort = Sort.by(Sort.Direction.DESC, "nombre");
        // Obteniendo una paginacion
        // PageRequest.of(pagina_inicio, cantidad_registros);
        Pageable pageable = PageRequest.of(0, 2, sort);

        Page<Categoria> categorias = this.categoriaRepository.findAll(pageable);

        categorias.forEach(categoria -> {
            System.out.println("Categoria encontrada=\t" + categoria);
        });

        System.out.println("categorias.getContent()=\t" + categorias.getContent());
        System.out.println("categorias.getTotalElements()=\t" + categorias.getTotalElements());
        System.out.println("categorias.getTotalPages()=\t" + categorias.getTotalPages());

    }


    private void actualizar(Integer id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            Categoria categoriaNueva = categoria.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            categoriaNueva.setNombre("Actualizada  alas " + localDateTime);
            this.categoriaRepository.save(categoriaNueva);
            System.out.println("Categoria actualizada=\t" + categoriaNueva);
        }
    }

    private void eliminar(Integer id) {
        this.categoriaRepository.deleteById(id);
        System.out.println("Categoria eliminada por id=\t" + id);
    }

    private void elimiarTodos() {
        // this.categoriaRepository.deleteAll(); // genere un delete por cada id
        // Elimina todos
        this.categoriaRepository.deleteAllInBatch();
        System.out.println("Eliminado todo");
    }

    private void existePorId(Integer id) {
        boolean existe = this.categoriaRepository.existsById(id);
        System.out.println("Existe por id=\t" + existe);
    }

    private void guardarMasivo() {
        List<Categoria> categorias = Arrays.asList(
                new Categoria(null, "Software", "Sof"),
                new Categoria(null, "Bitocin", "BTC")
        );

        this.categoriaRepository.saveAll(categorias);
        System.out.println("Guardado masivo");
    }


    private void imprimirTitulos(String titulo) {
        System.out.println("=".repeat(50));
        System.out.println(titulo);
        System.out.println("=".repeat(50));
    }


}

