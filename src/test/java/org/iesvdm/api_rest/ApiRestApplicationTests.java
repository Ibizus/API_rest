package org.iesvdm.api_rest;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.repository.BodaRepository;
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

    Usuario admin;
    Usuario user;
    Boda boda1;
    Boda boda2;
    Menu menuInfantil;
    Menu menuVegano;
    Menu menuCarne;
    Menu menuPescado;

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
    @Order(2)
    void crearBodas() {

//        boda1 = new Boda(0, "Mi Boda", LocalDate.now(), LocalTime.now(), "Alvaro", "Loli", "Finca Las Yeguas", "", "29600", "Yecla", "Malaga", new HashSet<>(), user);
//        boda2 = new Boda(0, "Boda Cuñis", LocalDate.now(), LocalTime.now(), "Paco", "Caro", "Finca El Agua", "", "29720", "Marbella", "Malaga", new HashSet<>(), user);
//        bodaRepository.save(boda1);
//        bodaRepository.save(boda2);
    }

    @Test
    @Order(3)
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
    @Order(4)
    void asociaciones() {
        // AÑADE MENUS A BODAS:
        boda1.getMenus().forEach(System.out::println);
        user.getBodas().forEach(System.out::println);
    }
}
