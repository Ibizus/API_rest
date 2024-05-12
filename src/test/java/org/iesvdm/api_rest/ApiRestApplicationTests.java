package org.iesvdm.api_rest;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Evento;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.repository.BodaRepository;
import org.iesvdm.api_rest.repository.EventoRepository;
import org.iesvdm.api_rest.repository.MenuRepository;
import org.iesvdm.api_rest.repository.UsuarioRepository;
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
    void asociaciones() {
        // AÑADE MENUS A BODAS:
        user.getBodas().add(boda1);
        user.getBodas().forEach(System.out::println);
        boda1.getMenus().forEach(System.out::println);
    }
}
