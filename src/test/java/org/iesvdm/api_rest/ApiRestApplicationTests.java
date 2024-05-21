package org.iesvdm.api_rest;

import org.iesvdm.api_rest.domain.*;
import org.iesvdm.api_rest.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.Normalizer;
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

        String[] listaInvitaciones = {
                "Francisco", "Antonio", "José", "Manuel", "María", "Ana", "Carmen", "Elena", "Isabel",
                "Laura", "David", "Javier", "Daniel", "Sofía", "Luis", "Miguel", "Rosa", "Carlos",
                "Pedro", "Raúl", "Andrea", "Patricia", "Lucía", "Diego", "Ángela", "Natalia", "Roberto",
                "Cristina", "Jorge", "Beatriz", "Fernando", "Silvia", "Pablo", "Verónica", "Rubén",
                "María José", "Adrián", "Eva", "José Antonio", "María Teresa", "Rafael", "Celia",
                "Alejandro", "María Isabel", "Ignacio", "Carmen María", "Alberto", "María Luisa",
                "Víctor", "Ana María", "Óscar", "María Pilar", "Guillermo", "María Dolores", "Mario",
                "María Ángeles", "Irene", "María Victoria", "Hugo", "María Elena", "Javier", "María Carmen",
                "Izan", "María Consuelo", "Aitana", "María Emilia", "Alba", "María Milagros", "Olivia",
                "María Candelaria", "Emma", "María Esperanza", "Valentina", "María África", "Luna",
                "María Gracia", "Ainhoa", "María Lourdes", "Cayetana", "María del Mar", "Leire",
                "María del Carmen", "Nora", "María del Pilar", "Alonso", "María de los Ángeles", "Mateo",
                "Leo", "Eric", "Izan", "Ariadna", "Noa", "Enzo", "Marco", "Ian"
        };

        for (String nombreInvitado : listaInvitaciones) {
            String nombreCorreo = Normalizer.normalize(nombreInvitado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s+", "").toLowerCase();
            Invitacion invitacion = new Invitacion(0, nombreInvitado, nombreCorreo+"@email.com", false, "", boda1, null);
            invitacionRepository.save(invitacion);
        }
    }

    @Test
    @Order(7)
    void crearRegalos() {

        String[] listaRegalos = {
                "Viaje", "Lavadora", "Carrito bebe", "Television", "Bicicleta",
                "Suscripcion Netflix", "Cesta productos artesanos", "Juego de sábanas",
                "Robot de cocina", "Freidora de aire", "Altavoz bluetooth", "Juegos de cuchillos",
                "Portarretratos", "Copas", "Marcos para fotos", "Obras de arte", "Cojines decorativos",
                "Lámparas de mesa", "Relojes de pared", "Cafetera", "Robot de cocina", "Licuadora y picadora",
                "Robot de limpieza", "Minibar", "Plancha de ropa", "Procesador de alimentos",
                "Cuchillos y tablas de cortar", "Jarras, licoreras, posavasos", "Sartenes y ollas",
                "Coladores y refractarios", "Azucareros, saleros y pimenteros", "Aceitera y vinagrera",
                "Juego de sábanas", "Robot aspirador", "Set de copas de vino", "Caja de herramientas",
                "Set de toallas", "Licuadora de mano", "Set de especias", "Tostadora", "Batería de cocina",
                "Caja de vinos", "Set de velas aromáticas", "Tabla de quesos", "Licor premium", "Set de tazas de café",
                "Caja de bombones", "Set de organizadores", "Set de cubiertos", "Caja de té o café", "Set de vasos",
                "Caja de aceites gourmet", "Set de manteles", "Set de copas de champagne", "Caja de productos gourmet",
                "Set de posavasos", "Set de platos", "Caja de vinos personalizada", "Set de servilletas",
                "Set de copas de cóctel", "Caja de chocolates"
        };

        for (String nombreRegalo : listaRegalos) {
            Regalo regalo = new Regalo(0, nombreRegalo, false, boda1);
            regaloRepository.save(regalo);
        }

    }

    @Test
    @Order(8)
    void crearTareas() {
        Tarea tare1 = new Tarea(0, "Disenar invitaciones", LocalDate.of(2024, 10, 15), false, boda1);
        tareaRepository.save(tare1);
    }

    @Test
    @Order(9)
    void crearInvitados() {

        String[] listaApellidoss = {
               "García", "Rodríguez", "González", "Fernández", "López", "Martínez", "Sánchez", "Pérez",
                "Gómez", "Jiménez", "Hernández", "Ruiz", "Díaz", "Moreno", "Muñoz", "Álvarez", "Romero",
                "Gutiérrez", "Alonso", "Navarro", "Torres", "Domínguez", "Ramos", "Vázquez", "Ramírez",
                "Gil", "Serrano", "Morales", "Molina", "Blanco", "Suárez", "Castro", "Ortega", "Delgado",
                "Ortiz", "Marín", "Rubio", "Núñez", "Medina", "Sanz", "Castillo", "Iglesias", "Cortés",
                "Garrido", "Santos", "Guerrero", "Lozano", "Cano", "Cruz", "Méndez", "Flores", "Prieto",
                "Herrera", "Peña", "León", "Márquez", "Cabrera", "Gallego", "Calvo", "Vidal", "Campos",
                "Reyes", "Vega", "Fuentes", "Carrasco", "Diez", "Aguilar", "Caballero", "Nieto", "Santana",
                "Vargas", "Pascual", "Giménez", "Herrero", "Hidalgo", "Montero", "Lorenzo", "Santiago",
                "Benítez", "Durán", "Ibáñez", "Arias", "Mora", "Ferrer", "Carmona", "Vicente", "Rojas",
                "Soto", "Crespo", "Román", "Pastor", "Velasco", "Parra", "Sáez", "Moya", "Bravo", "Rivera",
                "Gallardo"
        };
    }

    @Test
    @Order(99)
    void asociaciones() {
        // AÑADE MENUS A BODAS:
        //user.getBodas().add(boda1);
        System.out.println("Bodas del user 1: ");
        user.getBodas().forEach(System.out::println);
        System.out.println("Menus de la boda: ");
        boda1.getMenus().forEach(System.out::println);
        System.out.println("Tareas boda: ");
        boda1.getTareas().forEach(System.out::println);
        System.out.println("Regalos boda: ");
        boda1.getRegalos().forEach(System.out::println);
        System.out.println("Invitaciones boda: ");
        boda1.getInvitaciones().forEach(System.out::println);
    }


}
