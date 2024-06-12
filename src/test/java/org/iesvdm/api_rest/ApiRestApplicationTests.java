package org.iesvdm.api_rest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.api_rest.controller.UserController;
import org.iesvdm.api_rest.domain.*;
import org.iesvdm.api_rest.repository.*;
import org.iesvdm.api_rest.service.AuthService;
import org.iesvdm.api_rest.service.UserService;
import org.iesvdm.api_rest.util.UtilLazy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiRestApplicationTests {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
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
    @Autowired
    FAQRepository faqRepository;
    @PersistenceContext
    EntityManager entityManager;

    User admin;
    User user;
    User user2;
    Wedding wedding1;
    Wedding wedding2;
    Wedding wedding3;
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
    @Autowired
    private UserController userController;


//    @BeforeEach
//    void inicializa(){
////        user = userRepository.findById(1L).orElse(null);
////        wedding1 = weddingRepository.findById(1L).orElse(null);
//    }

//    @Test
//    @Order(1)
//    void contextLoads() {
//    }

//    public class RegisterRequest {
//        private String username;
//        private String password;
//        private String email;
//        private Set<String> roles;
//    }
//    src/main/resources/rolesInitialization.sql

    @Test
    @Order(1)
    @Sql(scripts = "/rolesInitialization.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void crearUsers(){

        // CREATE ADMIN FIRST:
        Set<String> adminRole = new HashSet<>();
        adminRole.add("admin");
        RegisterRequest newAdmin = new RegisterRequest("admin", "123456", "appguestify@gmail.com", adminRole);
        authService.registerUser(newAdmin);

        // THEN REGISTER SOME USERS:
        RegisterRequest newUser = new RegisterRequest("Jose", "123456", "josealzdz@gmail.com", null);
        Map<String, Object> responseMap = authService.registerUser(newUser);
        if (responseMap != null && responseMap.containsKey("id")) {
            User createdUSer = userService.one((Long) responseMap.get("id"));
            createdUSer.setLastName1("Lopez");
            createdUSer.setLastName2("Diaz");
            createdUSer.setAddress("calle Enrique Granados 96");
            createdUSer.setPostalCode("28660");
            createdUSer.setCity("Boadilla");
            createdUSer.setRegion("Madrid");
            createdUSer.setPhoneNumber("666555444");
            userRepository.save(createdUSer);
        }

        RegisterRequest newUser2 = new RegisterRequest("Hector", "123456", "hector.ldz@gmail.com", null);
        Map<String, Object> responseMap2 = authService.registerUser(newUser2);
        if (responseMap2 != null && responseMap2.containsKey("id")) {
            User createdUSer2 = userService.one((Long) responseMap2.get("id"));
            createdUSer2.setLastName1("Lopez");
            createdUSer2.setLastName2("Diaz");
            createdUSer2.setAddress("calle Los Pacos 29");
            createdUSer2.setPostalCode("29640");
            createdUSer2.setCity("Fuengirola");
            createdUSer2.setRegion("Málaga");
            createdUSer2.setPhoneNumber("666222333");
            userRepository.save(createdUSer2);
        }

//        admin = new User(0L, "Hector", "Lopez", "Diaz", "calle Veleta 2", "", "29651", "Mijas", "Malaga", "665661519", "hector.ldz@gmail.com", "123456", new HashSet<>(), new HashSet<>());
//        user = new User(0L, "Alvaro", "Moreno", "Barreiro", "calle de la Luz 25", "", "29640", "Fuengirola", "Malaga", "666666666", "alvaro@educaand.es", "123456", new HashSet<>(), new HashSet<>());
//        userRepository.save(admin);
//        userRepository.save(user);
    }

    @Test
    @Order(2)
    void crearWeddings() {
        user = userRepository.findById(2L).orElse(null);

        wedding1 = new Wedding(0, "Mi Boda", LocalDate.of(2024, 6, 22), LocalTime.of(17,30), "Alvaro", "Loli", "Finca Las Yeguas", "", "29600", "Yecla", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        wedding2 = new Wedding(0, "Boda Cuñis", LocalDate.of(2024, 9, 15), LocalTime.of(12,30), "Paco", "Caro", "Finca El Agua", "", "29720", "Marbella", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        wedding3 = new Wedding(0, "Boda de Masu", LocalDate.of(2026, 7, 10), LocalTime.of(19,0), "María Jesús", "Pedro", "Finca O Labrego", "", "63705", "Santiago de Compostela", "Galicia", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        weddingRepository.save(wedding1);
        weddingRepository.save(wedding2);
        weddingRepository.save(wedding3);

        user2 = userRepository.findById(3L).orElse(null);
        Wedding wedding4 = new Wedding(0, "Boda de Ana y Luis", LocalDate.of(2024, 6, 30), LocalTime.of(17,30), "Ana", "Luis", "Hacienda San Rafael", "", "41770", "Sevilla", "Andalucía", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user2);
        Wedding wedding5 = new Wedding(0, "Boda de Laura y Miguel", LocalDate.of(2025, 4, 22), LocalTime.of(12,45), "Laura", "Miguel", "Cortijo El Sotillo", "", "04100", "Almería", "Andalucía", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user2);
        weddingRepository.save(wedding4);
        weddingRepository.save(wedding5);
    }

    @Test
    @Order(3)
    void crearEvents() {
        wedding1 = weddingRepository.findById(2L).orElse(null);

        ceremonia = new Event(0, "Iglesia Niño Jesús", "Celebración del matrimonio", LocalTime.of(18, 0), wedding1);
        cocktail = new Event(0, "Jardín de la Finca", "Bienvenida a la finca", LocalTime.of(19, 30), wedding1);
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
//        wedding1 = weddingRepository.findById(2L).orElse(null);
        List<Wedding> weddings = weddingRepository.findAll();

        Menu menuInfantil = new Menu(0, "Infantil", "Macarrones con queso", "Salchichas", "Yogur", null);
        Menu menuVegano = new Menu(0, "Vegano", "Ensalada", "Lasaña vegana", "Pastel de manzana vegano", null);
        Menu menuCarne = new Menu(0, "Carne", "Crema de Remolacha", "Solomillo", "Coulant", null);
        Menu menuPescado = new Menu(0, "Pescado", "Ensalada", "Lubina a la sal", "Tarta de queso", null);
        Menu menuGourmet1 = new Menu(0, "Carne Gourmet", "Carpaccio de ternera con parmesano", "Lomo de cordero con reducción de vino tinto", "Tarta de chocolate belga", null);
        Menu menuGourmet2 = new Menu(0, "Gourmet", "Foie gras con mermelada de higos", "Filete mignon con puré trufado", "Soufflé de maracuyá", null);
        Menu menuGourmet3 = new Menu(0, "Marisco", "Tartar de atún rojo con aguacate", "Langosta Thermidor", "Crème brûlée de vainilla", null);

        List<Menu> menus = new ArrayList<>();
        menus.add(menuInfantil);
        menus.add(menuVegano);
        menus.add(menuCarne);
        menus.add(menuPescado);
        menus.add(menuGourmet1);
        menus.add(menuGourmet2);
        menus.add(menuGourmet3);

        weddings.forEach(wedding -> {
            UtilLazy.initializeLazyOneToManyByJoinFetch(
                    entityManager,
                    Wedding.class,
                    Menu.class,
                    wedding.getId(),
                    wedding::setMenus);
            wedding.getMenus().addAll(menus);
            weddingRepository.save(wedding);
        });

//        menuRepository.save(menuInfantil);
//        menuRepository.save(menuVegano);
//        menuRepository.save(menuCarne);
//        menuRepository.save(menuPescado);
    }


    @Test
    @Order(5)
    void crearInvitations() {
//        wedding1 = weddingRepository.findById(2L).orElse(null);
        List<Wedding> weddings = weddingRepository.findAll();

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

        weddings.forEach(wedding -> {
            for (String nombreInvitado : listaInvitations) {
                String nombreCorreo = Normalizer.normalize(nombreInvitado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s+", "").toLowerCase();
                Invitation invitation = new Invitation(0, nombreInvitado, nombreCorreo+"@email.com", false, "", wedding, null);
//                invitationRepository.save(invitation);
                weddingRepository.save(wedding);
            }

        });


    }

    @Test
    @Order(6)
    void crearGifts() {
        wedding1 = weddingRepository.findById(2L).orElse(null);

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
    @Order(7)
    void crearTasks() {
        wedding1 = weddingRepository.findById(2L).orElse(null);

        Task tarea1 = new Task(0, "Definir la lista de invitados", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea2 = new Task(0, "Reservar la ceremonia y el lugar de la recepción", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea3 = new Task(0, "Fijar un presupuesto", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea4 = new Task(0, "Infórmarse sobre los trámites necesarios", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea5 = new Task(0, "Seleccionar padrinos y damas de honor", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea6 = new Task(0, "Elegir los anillos de matrimonio", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea7 = new Task(0, "Centros de mesa", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea8 = new Task(0, "elegir catering", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea9 = new Task(0, "Contratar fotógrafo", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea10 = new Task(0, "Contratar dj", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea11 = new Task(0, "Ramo y flores de decoración", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea12 = new Task(0, "Planificar transporte", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea13 = new Task(0, "Diseñar invitaciones", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea14 = new Task(0, "Traje novio", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea15 = new Task(0, "Vestido novia", LocalDate.of(2024, 10, 15), false, wedding1);
        Task tarea16 = new Task(0, "Peluquería", LocalDate.of(2024, 10, 15), false, wedding1);

        taskRepository.save(tarea1);
        taskRepository.save(tarea2);
        taskRepository.save(tarea3);
        taskRepository.save(tarea4);
        taskRepository.save(tarea5);
        taskRepository.save(tarea6);
        taskRepository.save(tarea7);
        taskRepository.save(tarea7);
        taskRepository.save(tarea8);
        taskRepository.save(tarea9);
        taskRepository.save(tarea10);
        taskRepository.save(tarea11);
        taskRepository.save(tarea12);
        taskRepository.save(tarea13);
        taskRepository.save(tarea14);
        taskRepository.save(tarea15);
        taskRepository.save(tarea16);
    }

    @Test
    @Order(8)
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
    @Order(9)
    void crearFaqs(){
        FAQ faq1 = new FAQ(0,
                "¿Cómo funciona la gestión de invitados?",
                "Nuestro servicio de gestión de invitados te permite agregar, editar y organizar la lista de invitados de manera sencilla. Puedes enviar invitaciones digitales, realizar un seguimiento de las confirmaciones de asistencia (RSVP), y asignar asientos para la ceremonia y la recepción. Todo esto desde una interfaz intuitiva y fácil de usar.",
                false);
        FAQ faq2 = new FAQ(0,
                "¿Puedo personalizar el diseño de mi página de boda?",
                "Sí, ofrecemos varias plantillas de diseño que puedes personalizar para que coincidan con el estilo y el tema de tu boda. Puedes agregar tus fotos, cambiar colores y fuentes, y añadir secciones adicionales según tus necesidades. Queremos que tu página de boda sea única y refleje tu personalidad.",
                false);
        FAQ faq3 = new FAQ(0,
                "¿Es seguro utilizar su plataforma?",
                "La seguridad de tus datos es nuestra máxima prioridad. Utilizamos cifrado SSL para proteger toda la información que se transmite a través de nuestra plataforma. Además, tenemos políticas estrictas de privacidad para garantizar que tus datos personales y los de tus invitados estén protegidos en todo momento.",
                false);
        FAQ faq4 = new FAQ(0,
                "¿Puedo realizar cambios en la lista de invitados una vez enviada la invitación?",
                "Sí, puedes realizar cambios en la lista de invitados y en la organización de tu boda en cualquier momento. Nuestra plataforma está diseñada para ser flexible y adaptable a tus necesidades. Los cambios se actualizan automáticamente, y tus invitados recibirán notificaciones de cualquier modificación importante, asegurando que todos estén al tanto.",
                false);
        FAQ faq5 = new FAQ(0,
                "¿Puedo integrar mi cuenta con redes sociales?",
                "Sí, ofrecemos integración con varias redes sociales para facilitar la comunicación y la compartición de información sobre tu boda. Puedes sincronizar tu cuenta con plataformas como Facebook, Instagram y Twitter, permitiendo que tus invitados compartan fotos y momentos especiales usando hashtags personalizados. Esto también ayuda a mantener a todos informados y entusiasmados con los detalles de tu gran día.",
                false);
        FAQ faq6 = new FAQ(0,
                "¿Ofrecen soporte técnico?",
                "Sí, ofrecemos soporte técnico para ayudarte en cualquier etapa de tu planificación. Nuestro equipo de soporte está disponible por correo electrónico, chat en vivo y teléfono para resolver cualquier problema o responder a cualquier pregunta que puedas tener. Queremos asegurarnos de que tu experiencia sea lo más fluida y agradable posible.",
                false);
        faqRepository.save(faq1);
        faqRepository.save(faq2);
        faqRepository.save(faq3);
        faqRepository.save(faq4);
        faqRepository.save(faq5);
        faqRepository.save(faq6);
    }

    @Test
    @Order(10)
    void testGetUserEmailByInvitationId() { // Test to try JPQL query:
        Long invitationId = 2L;
        Optional<String> userEmail = invitationRepository.findUserEmailByInvitationId(invitationId);
        assertThat(userEmail).isPresent();
        assertThat(userEmail.get()).isEqualTo("josealzdz@gmail.com");
    }

    @Test
    @Order(99)
    void asociaciones() {

//        System.out.println("Weddings del user 1: ");
//        user.getWeddings().forEach(System.out::println);
//        System.out.println("Menus de la wedding: ");
//        wedding1.getMenus().forEach(System.out::println);
//        System.out.println("Tasks wedding: ");
//        wedding1.getTasks().forEach(System.out::println);
//        System.out.println("Gifts wedding: ");
//        wedding1.getGifts().forEach(System.out::println);
//        System.out.println("Invitations wedding: ");
//        wedding1.getInvitations().forEach(System.out::println);
    }

}
