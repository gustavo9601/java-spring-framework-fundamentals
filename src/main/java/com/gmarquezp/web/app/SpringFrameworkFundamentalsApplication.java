package com.gmarquezp.web.app;

import com.gmarquezp.web.app.aop.TargetObject;
import com.gmarquezp.web.app.autowire_listas.Figura;
import com.gmarquezp.web.app.models.relaciones.Categoria;
import com.gmarquezp.web.app.models.relaciones.Perfil;
import com.gmarquezp.web.app.models.relaciones.UsuarioP;
import com.gmarquezp.web.app.models.relaciones.Vacante;
import com.gmarquezp.web.app.models.repositories.ICategoriaRepository;
import com.gmarquezp.web.app.models.repositories.IPerfilRepository;
import com.gmarquezp.web.app.models.repositories.IUsuarioRepository;
import com.gmarquezp.web.app.models.repositories.IVacanteRepository;
import com.gmarquezp.web.app.perfiles.IEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@SpringBootApplication
public class SpringFrameworkFundamentalsApplication implements CommandLineRunner {


    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private IVacanteRepository vacanteRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPerfilRepository perfilRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /*
    * Devolvera todas las implementaciones que tenga la interfaz
    * */
    @Autowired
    private List<Figura> figuras;

    @Autowired
    private TargetObject targetObject;

    private static Logger logger = Logger.getLogger(SpringFrameworkFundamentalsApplication.class.getName());

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringFrameworkFundamentalsApplication.class, args);


        /*
         * Manejo de perfil spring
         * */

        IEnvironmentService environmentService = context.getBean(IEnvironmentService.class); // Obtiene el bean inyectado
        logger.info("Get environment: " + environmentService.getEnvironment());
    }

    /*
     * Se ejecutara cuando se inicialice la app
     * */
    @Override
    public void run(String... args) throws Exception {
        /*
         * Categorias
         * */

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
        // this.imprimirTitulos("Eliminar todos");
        // this.elimiarTodos();
        // this.imprimirTitulos("Eliminando categoria por id");
        // this.eliminar(2);
        this.imprimirTitulos("Guardado masivo");
        this.guardarMasivo();
        this.imprimirTitulos("Crear vacante");
        this.crearVacante();



        /*
         * Vacantes
         * */
        this.imprimirTitulos("Todas las vacantes");
        this.buscarVacantes();
        this.imprimirTitulos("Vacantes por estatus");
        this.buscarVacantesPorEstatus();


        /*
         * Perfiles
         * */
        this.imprimirTitulos("Crear perfiles");
        this.crearPerfiles();


        /*
         * Usuarios y perfiles
         * */
        this.imprimirTitulos("Crear usuarios y perfiles");
        this.crearUsuarioConPerfil();
        this.imprimirTitulos("Buscar usuario por id");
        this.buscarUsuariosConPerfil();

        // Ejemplo contraseña Encriptada
        this.imprimirTitulos("Contraseña encriptada");
        this.ejemploContraseñEncriptada();


        /*
        * Listas de autowired
        * */
        this.imprimirTitulos("Listas de autowired");
        this.figuras.forEach((Figura figura) -> {
            System.out.println("figura area=\t " + figura.calcularArea());
        });


        /*
        * AOP Aspectos
        * */
        this.imprimirTitulos("AOP Aspectos");
        this.targetObject.saludando();

        this.targetObject.despedida();
    }




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


    private void buscarVacantes() {
        List<Vacante> vacantes = this.vacanteRepository.findAll();
        vacantes.forEach(vacante -> {
            System.out.println("Vacante encontrada=\t" + vacante.getNombre() + " | Categoria=\t" + vacante.getCategoria().getNombre());
        });
    }

    private void crearVacante() {
        Vacante vacante = new Vacante();
        vacante.setNombre("Desarrollador Java");
        vacante.setDescripcion("Descripcion de la vacante");
        vacante.setEstatus("Aprobada");
        vacante.setSalario(5000.0D);
        vacante.setDestacado(1);
        vacante.setFecha(new Date());


        Categoria categoria = new Categoria(28, null, null);
        vacante.setCategoria(categoria);

        this.vacanteRepository.save(vacante);
        System.out.println("Vacante creada=\t" + vacante);
    }

    private List<Perfil> listaPerfiles() {
        return Arrays.asList(
                new Perfil(null, "ADMIN"),
                new Perfil(null, "USER"),
                new Perfil(null, "SUPERVISOR")
        );
    }

    private void crearPerfiles() {
        this.perfilRepository.saveAll(this.listaPerfiles());
        System.out.println("Perfiles creados");
    }

    private void crearUsuarioConPerfil() {
        UsuarioP usuarioP = new UsuarioP();
        LocalDateTime now = LocalDateTime.now();
        usuarioP.setNombre("Juan");
        usuarioP.setUsername("full_juanito - " + now);
        usuarioP.setEmail("test@tes" + now + ".com");
        usuarioP.setFechaRegistro(new Date());
        usuarioP.setPassword("123456");
        usuarioP.setEstatus(1);

        Perfil perfil1 = new Perfil(1, null);
        Perfil perfil2 = new Perfil(2, null);

        usuarioP.agregarPerfil(perfil1)
                .agregarPerfil(perfil2);

        this.usuarioRepository.save(usuarioP);
        System.out.println("Usuario creado con los perfiles asginados=\t" + usuarioP);


    }


    private void buscarUsuariosConPerfil() {
        UsuarioP usuarioP = this.usuarioRepository.findById(2).get();
        System.out.println("Usuario encontrado=\t" + usuarioP.getNombre());
        System.out.println("= Perfiles =");
        usuarioP.getPerfiles().forEach(perfil -> {
            System.out.println("Perfil encontrado=\t" + perfil.getPerfil());
        });
    }

    private void buscarVacantesPorEstatus(){
        this.vacanteRepository.findByEstatus("Aprobada").forEach(vacante -> {
            System.out.println("Vacante encontrada=\t" + vacante.getNombre());
        });
    }

    private void ejemploContraseñEncriptada(){
        String contrasenaEncriptada = this.passwordEncoder.encode("Hello World");
        System.out.println("contrasenaEncriptada = " + contrasenaEncriptada);
    }

    private void imprimirTitulos(String titulo) {
        System.out.println("=".repeat(50));
        System.out.println(titulo);
        System.out.println("=".repeat(50));
    }


}

