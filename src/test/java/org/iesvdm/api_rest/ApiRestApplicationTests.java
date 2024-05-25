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
    UserRepository userRepository;
    @Autowired
    WeddingRepository weddingRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    InvitationRepository invitationRepository;
    @Autowired
    GiftRepository giftRepository;

    User admin;
    User user;
    Wedding wedding1;
    Wedding wedding2;
    Menu menuInfantil;
    Menu menuVegano;
    Menu menuCarne;
    Menu menuPescado;
    Event ceremonia;
    Event cocktail;
    Event banquete;
    Event fiesta;
    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    void inicializa(){
        user = userRepository.findById(1L).orElse(null);
        wedding1 = weddingRepository.findById(1L).orElse(null);
    }

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @Order(2)
    void crearUsers(){
        admin = new User(0, "Hector", "Lopez", "Diaz", "calle Veleta 2", "", "29651", "Mijas", "Malaga", "hlopdia699@g.educaand.es","665661519", new HashSet<>());
        user = new User(0, "Alvaro", "Moreno", "Barreiro", "calle de la Luz 25", "", "29640", "Fuengirola", "Malaga", "alvaro@educaand.es","666666666", new HashSet<>());
        userRepository.save(admin);
        userRepository.save(user);
    }

    @Test
    @Order(3)
    void crearEvents() {
        ceremonia = new Event(0, "Iglesia Niño Jesús", "Celebración del matrimonio", LocalTime.of(18, 0), wedding1);
        cocktail = new Event(0, "Jardín Finca Las Yeguas", "Bienvenida a la finca", LocalTime.of(19, 30), wedding1);
        banquete = new Event(0, "Salón interior", "Banquete de weddings", LocalTime.of(21, 0), wedding1);
        fiesta = new Event(0, "Discoteca", "Espectáculo y barra libre para todos", LocalTime.of(23, 30), wedding1);
        eventRepository.save(ceremonia);
        eventRepository.save(cocktail);
        eventRepository.save(banquete);
        eventRepository.save(fiesta);
    }

    @Test
    @Order(4)
    void crearMenus() {
        menuInfantil = new Menu(0, "Infantil", "Macarrones con queso", "Salchichas", "Yogur", wedding1);
        menuVegano = new Menu(0, "Vegano", "Ensalada", "Lasaña vegana", "Pastel de manzana vegano", wedding1);
        menuCarne = new Menu(0, "Carne", "Crema de Remolacha", "Solomillo", "Coulant", wedding1);
        menuPescado = new Menu(0, "Pescado", "Ensalada", "Lubina a la sal", "Tarta de queso", wedding1);
        menuRepository.save(menuInfantil);
        menuRepository.save(menuVegano);
        menuRepository.save(menuCarne);
        menuRepository.save(menuPescado);
    }

    @Test
    @Order(5)
    void crearWeddings() {

        wedding1 = new Wedding(0, "Mi Wedding", LocalDate.now(), LocalTime.now(), "Alvaro", "Loli", "Finca Las Yeguas", "", "29600", "Yecla", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        wedding2 = new Wedding(0, "Wedding Cuñis", LocalDate.now(), LocalTime.now(), "Paco", "Caro", "Finca El Agua", "", "29720", "Marbella", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        weddingRepository.save(wedding1);
        weddingRepository.save(wedding2);
    }

    @Test
    @Order(6)
    void crearInvitations() {

        String[] listaInvitations = {
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

        for (String nombreInvitado : listaInvitations) {
            String nombreCorreo = Normalizer.normalize(nombreInvitado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s+", "").toLowerCase();
            Invitation invitation = new Invitation(0, nombreInvitado, nombreCorreo+"@email.com", false, "", wedding1, null);
            invitationRepository.save(invitation);
        }
    }

    @Test
    @Order(7)
    void crearGifts() {

        String[] listaGifts = {
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

        for (String nombreRegalo : listaGifts) {
            Gift regalo = new Gift(0, nombreRegalo, false, wedding1);
            giftRepository.save(regalo);
        }

    }

    @Test
    @Order(8)
    void crearTasks() {
        Task tare1 = new Task(0, "Disenar invitations", LocalDate.of(2024, 10, 15), false, wedding1);
        taskRepository.save(tare1);
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
        //user.getWeddings().add(wedding1);
        System.out.println("Weddings del user 1: ");
        user.getWeddings().forEach(System.out::println);
        System.out.println("Menus de la wedding: ");
        wedding1.getMenus().forEach(System.out::println);
        System.out.println("Tasks wedding: ");
        wedding1.getTasks().forEach(System.out::println);
        System.out.println("Gifts wedding: ");
        wedding1.getGifts().forEach(System.out::println);
        System.out.println("Invitations wedding: ");
        wedding1.getInvitations().forEach(System.out::println);
    }


}
