package org.iesvdm.api_rest;

import org.iesvdm.api_rest.domain.*;
import org.iesvdm.api_rest.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiRestApplicationTests {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    BodaRepository bodaRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    InvitacionRepository invitacionRepository;
    @Autowired
    RegaloRepository regaloRepository;

    Usuario admin;
    Usuario user;
    Boda boda1;
    Boda boda2;
    Menu menuInfantil;
    Menu menuVegano;
    Menu menuCarne;
    Menu menuPescado;
    Evento ceremonia;
    Evento cocktail;
    Evento banquete;
    Evento fiesta;
    @Autowired
    private TareaRepository tareaRepository;


    @BeforeEach
    void inicializa(){
        user = usuarioRepository.findById(1L).orElse(null);
        boda1 = bodaRepository.findById(1L).orElse(null);
    }

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @Order(2)
    void crearUsuarios(){
        admin = new Usuario(0, "Hector", "Lopez", "Diaz", "calle Veleta 2", "", "29651", "Mijas", "Malaga", "hlopdia699@g.educaand.es","665661519", new HashSet<>());
        user = new Usuario(0, "Alvaro", "Moreno", "Barreiro", "calle de la Luz 25", "", "29640", "Fuengirola", "Malaga", "alvaro@educaand.es","666666666", new HashSet<>());
        usuarioRepository.save(admin);
        usuarioRepository.save(user);
    }

    @Test
    @Order(3)
    void crearEventos() {
        ceremonia = new Evento(0, "Iglesia Niño Jesús", "Celebración del matrimonio", LocalTime.of(18, 0), boda1);
        cocktail = new Evento(0, "Jardín Finca Las Yeguas", "Bienvenida a la finca", LocalTime.of(19, 30), boda1);
        banquete = new Evento(0, "Salón interior", "Banquete de bodas", LocalTime.of(21, 0), boda1);
        fiesta = new Evento(0, "Discoteca", "Espectáculo y barra libre para todos", LocalTime.of(23, 30), boda1);
        eventoRepository.save(ceremonia);
        eventoRepository.save(cocktail);
        eventoRepository.save(banquete);
        eventoRepository.save(fiesta);
    }

    @Test
    @Order(4)
    void crearMenus() {
        menuInfantil = new Menu(0, "Infantil", "Macarrones con queso", "Salchichas", "Yogur", boda1);
        menuVegano = new Menu(0, "Vegano", "Ensalada", "Lasaña vegana", "Pastel de manzana vegano", boda1);
        menuCarne = new Menu(0, "Carne", "Crema de Remolacha", "Solomillo", "Coulant", boda1);
        menuPescado = new Menu(0, "Pescado", "Ensalada", "Lubina a la sal", "Tarta de queso", boda1);
        menuRepository.save(menuInfantil);
        menuRepository.save(menuVegano);
        menuRepository.save(menuCarne);
        menuRepository.save(menuPescado);
    }

    @Test
    @Order(5)
    void crearBodas() {

        boda1 = new Boda(0, "Mi Boda", LocalDate.now(), LocalTime.now(), "Alvaro", "Loli", "Finca Las Yeguas", "", "29600", "Yecla", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        boda2 = new Boda(0, "Boda Cuñis", LocalDate.now(), LocalTime.now(), "Paco", "Caro", "Finca El Agua", "", "29720", "Marbella", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        bodaRepository.save(boda1);
        bodaRepository.save(boda2);
    }

    @Test
    @Order(6)
    void crearInvitaciones() {
        Invitacion invitacion1 = new Invitacion(0, "Antonio", "antofenomeno@example.com", false, "", boda1, null);
        invitacionRepository.save(invitacion1);
    }

    @Test
    @Order(7)
    void crearRegalos() {
        Regalo regalo1 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo2 = new Regalo(0, "Lavadora", false, boda1);
        Regalo regalo3 = new Regalo(0, "Carrito bebe", false, boda1);
        Regalo regalo4 = new Regalo(0, "Television", false, boda1);
        Regalo regalo5 = new Regalo(0, "Bicicleta", false, boda1);
        Regalo regalo6 = new Regalo(0, "Suscripcion", false, boda1);
        Regalo regalo7 = new Regalo(0, "Cesta productos artesanos", false, boda1);
        Regalo regalo8 = new Regalo(0, "Reloj", false, boda1);
        Regalo regalo9 = new Regalo(0, "Zapatos", false, boda1);
        Regalo regalo10 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo11 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo12 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo13 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo14 = new Regalo(0, "Viaje", false, boda1);
        Regalo regalo15 = new Regalo(0, "Viaje", false, boda1);

        regaloRepository.save(regalo1);
    }

    @Test
    @Order(8)
    void crearTareas() {
        Tarea tare1 = new Tarea(0, "Disenar invitaciones", LocalDate.of(2024, 10, 15), false, boda1);
        tareaRepository.save(tare1);
    }

    @Test
    @Order(99)
    void asociaciones() {
        // AÑADE MENUS A BODAS:
        user.getBodas().add(boda1);
        System.out.println("Bodas del user 1: ");
        user.getBodas().forEach(System.out::println);
        System.out.println("Menus de la boda: ");
        boda1.getMenus().forEach(System.out::println);
        System.out.println("Tareas boda: ");
        boda1.getTareas().forEach(System.out::println);
        System.out.println("Regalos boda: ");
        boda1.getRegalos().forEach(System.out::println);
    }


}
