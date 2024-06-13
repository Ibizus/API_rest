package org.iesvdm.api_rest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assert;
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
    }

    @Test
    @Order(2)
    void crearWeddings() {

        user = userRepository.findById(2L).orElse(null);
        Wedding wedding1 = new Wedding(0, "Mi Boda", LocalDate.of(2024, 6, 22), LocalTime.of(17,30), "Alvaro", "Loli", "Finca Las Yeguas", "", "29600", "Yecla", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        Wedding wedding2 = new Wedding(0, "Boda Cuñis", LocalDate.of(2024, 9, 15), LocalTime.of(12,30), "Paco", "Caro", "Finca El Agua", "", "29720", "Marbella", "Malaga", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
        Wedding wedding3 = new Wedding(0, "Boda de Masu", LocalDate.of(2026, 7, 10), LocalTime.of(19,0), "María Jesús", "Pedro", "Finca O Labrego", "", "63705", "Santiago de Compostela", "Galicia", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), user);
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
        List<Wedding> weddings = weddingRepository.findAll();

        ceremonia = new Event(0, "Iglesia Niño Jesús", "Celebración del matrimonio", LocalTime.of(18, 0), weddings.get(0));
        cocktail = new Event(0, "Jardín de la Finca", "Bienvenida a la finca", LocalTime.of(19, 30), weddings.get(0));
        banquete = new Event(0, "Salón interior", "Banquete de weddings", LocalTime.of(21, 0), weddings.get(0));
        fiesta = new Event(0, "Discoteca", "Espectáculo y barra libre para todos", LocalTime.of(23, 30), weddings.get(0));
        eventRepository.save(ceremonia);
        eventRepository.save(cocktail);
        eventRepository.save(banquete);
        eventRepository.save(fiesta);
        UtilLazy.initializeLazyOneToManyByJoinFetch(
                entityManager,
                Wedding.class,
                Event.class,
                weddings.get(0).getId(),
                weddings.get(0)::setEvents);
        weddings.get(0).getEvents().add(ceremonia);
        weddings.get(0).getEvents().add(cocktail);
        weddings.get(0).getEvents().add(banquete);
        weddings.get(0).getEvents().add(fiesta);
        weddingRepository.save(weddings.get(0));

        Event ceremonia2 = new Event(0, "Catedral de Santa María", "Ceremonia de la boda", LocalTime.of(17, 0), weddings.get(1));
        Event cocktail2 = new Event(0, "Terraza del Hotel Royal", "Cóctel de bienvenida", LocalTime.of(18, 30), weddings.get(1));
        Event banquete2 = new Event(0, "Gran Salón", "Cena y brindis", LocalTime.of(20, 0), weddings.get(1));
        Event fiesta2 = new Event(0, "Salón de fiestas", "Baile y barra libre", LocalTime.of(22, 30), weddings.get(1));
        eventRepository.save(ceremonia2);
        eventRepository.save(cocktail2);
        eventRepository.save(banquete2);
        eventRepository.save(fiesta2);
        UtilLazy.initializeLazyOneToManyByJoinFetch(
                entityManager,
                Wedding.class,
                Event.class,
                weddings.get(1).getId(),
                weddings.get(1)::setEvents);
        weddings.get(1).getEvents().add(ceremonia2);
        weddings.get(1).getEvents().add(cocktail2);
        weddings.get(1).getEvents().add(banquete2);
        weddings.get(1).getEvents().add(fiesta2);
        weddingRepository.save(weddings.get(1));

        Event ceremonia3 = new Event(0, "Parroquia San Juan", "Misa de bodas", LocalTime.of(16, 0), weddings.get(2));
        Event cocktail3 = new Event(0, "Patio del Palacio", "Recepción de bienvenida", LocalTime.of(17, 30), weddings.get(2));
        Event banquete3 = new Event(0, "Jardines del Palacio", "Banquete al aire libre", LocalTime.of(19, 0), weddings.get(2));
        Event fiesta3 = new Event(0, "Salón de eventos", "Fiesta y baile", LocalTime.of(21, 30), weddings.get(2));
        eventRepository.save(ceremonia3);
        eventRepository.save(cocktail3);
        eventRepository.save(banquete3);
        eventRepository.save(fiesta3);
        UtilLazy.initializeLazyOneToManyByJoinFetch(
                entityManager,
                Wedding.class,
                Event.class,
                weddings.get(2).getId(),
                weddings.get(2)::setEvents);
        weddings.get(2).getEvents().add(ceremonia3);
        weddings.get(2).getEvents().add(cocktail3);
        weddings.get(2).getEvents().add(banquete3);
        weddings.get(2).getEvents().add(fiesta3);
        weddingRepository.save(weddings.get(2));

        Event ceremonia4 = new Event(0, "Capilla de la Virgen", "Ceremonia religiosa", LocalTime.of(15, 0), weddings.get(3));
        Event cocktail4 = new Event(0, "Jardín del Club", "Cóctel y aperitivos", LocalTime.of(16, 30), weddings.get(3));
        Event banquete4 = new Event(0, "Salón principal", "Comida de bodas", LocalTime.of(18, 0), weddings.get(3));
        Event fiesta4 = new Event(0, "Sala de fiestas", "Celebración y barra libre", LocalTime.of(20, 30), weddings.get(3));
        eventRepository.save(ceremonia4);
        eventRepository.save(cocktail4);
        eventRepository.save(banquete4);
        eventRepository.save(fiesta4);
        UtilLazy.initializeLazyOneToManyByJoinFetch(
                entityManager,
                Wedding.class,
                Event.class,
                weddings.get(3).getId(),
                weddings.get(3)::setEvents);
        weddings.get(3).getEvents().add(ceremonia4);
        weddings.get(3).getEvents().add(cocktail4);
        weddings.get(3).getEvents().add(banquete4);
        weddings.get(3).getEvents().add(fiesta4);
        weddingRepository.save(weddings.get(3));

        Event ceremonia5 = new Event(0, "Templo del Amor", "Ritual de matrimonio", LocalTime.of(17, 30), weddings.get(4));
        Event cocktail5 = new Event(0, "Terraza del Mirador", "Cóctel de bienvenida", LocalTime.of(19, 0), weddings.get(4));
        Event banquete5 = new Event(0, "Salón del Horizonte", "Cena de gala", LocalTime.of(20, 30), weddings.get(4));
        Event fiesta5 = new Event(0, "Salón de fiestas", "Baile y barra libre", LocalTime.of(22, 00), weddings.get(4));
        eventRepository.save(ceremonia5);
        eventRepository.save(cocktail5);
        eventRepository.save(banquete5);
        eventRepository.save(fiesta5);
        UtilLazy.initializeLazyOneToManyByJoinFetch(
                entityManager,
                Wedding.class,
                Event.class,
                weddings.get(4).getId(),
                weddings.get(4)::setEvents);
        weddings.get(4).getEvents().add(ceremonia5);
        weddings.get(4).getEvents().add(cocktail5);
        weddings.get(4).getEvents().add(banquete5);
        weddings.get(4).getEvents().add(fiesta5);
        weddingRepository.save(weddings.get(4));
    }

    @Test
    @Order(4)
    void crearMenus() {
        List<Wedding> weddings = weddingRepository.findAll();
        System.out.println("Encontradas " + weddings.size() + " bodas");

        Menu menuInfantil = new Menu(0, "Infantil", "Macarrones con queso", "Salchichas", "Yogur", null);
        Menu menuVegano = new Menu(0, "Vegano", "Ensalada", "Lasaña vegana", "Pastel de manzana vegano", null);
        Menu menuCarne = new Menu(0, "Carne", "Crema de Remolacha", "Solomillo", "Coulant", null);
        Menu menuPescado = new Menu(0, "Pescado", "Ensalada", "Lubina a la sal", "Tarta de queso", null);
        Menu menuGourmet1 = new Menu(0, "Carne Gourmet", "Carpaccio de ternera con parmesano", "Lomo de cordero con reducción de vino tinto", "Tarta de chocolate belga", null);
        Menu menuGourmet2 = new Menu(0, "Gourmet", "Foie gras con mermelada de higos", "Filete mignon con puré trufado", "Soufflé de maracuyá", null);
        Menu menuGourmet3 = new Menu(0, "Marisco", "Tartar de atún rojo con aguacate", "Langosta Thermidor", "Crème brûlée de vainilla", null);

        List<Menu> menusList = new ArrayList<>();
        menusList.add(menuInfantil);
        menusList.add(menuVegano);
        menusList.add(menuCarne);
        menusList.add(menuPescado);
        menusList.add(menuGourmet1);
        menusList.add(menuGourmet2);
        menusList.add(menuGourmet3);

        weddings.forEach(wedding -> {
            System.out.println("inicializando coleccion de la boda con id : " + wedding.getId());
            UtilLazy.initializeLazyOneToManyByJoinFetch(
                    entityManager,
                    Wedding.class,
                    Menu.class,
                    wedding.getId(),
                    wedding::setMenus);

            menusList.forEach(menu -> {
                System.out.println("Guardadndo menu en bbdd: " + menu.getName());
                menu.setId(0L);
                menu.setWedding(null);
                Menu newMenu = menuRepository.save(menu);
                System.out.println("Asociando boda " + wedding.getId() + " al menu " + newMenu.getName() + " con id : " + newMenu.getId());
                System.out.println("Menu en la lista tiene el ID: " + menu.getId());
                newMenu.setWedding(wedding);
                System.out.println("Añadiendo menu a la boda");
                wedding.getMenus().add(newMenu);
            });
            System.out.println("persistiendo boda con id : " + wedding.getId() + " con sus cambios");
            weddingRepository.save(wedding);
        });
    }


    @Test
    @Order(5)
    void crearInvitations() {

        List<Wedding> weddings = weddingRepository.findAll();
        String[] nameArray = {
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
            // Iniatialize collection:
            UtilLazy.initializeLazyOneToManyByJoinFetch(
                    entityManager,
                    Wedding.class,
                    Invitation.class,
                    wedding.getId(),
                    wedding::setInvitations);
            // create invitations collection:
            List<String> nameList = Arrays.asList(nameArray);
            Collections.shuffle(nameList);
            // Create Ramdon so every wedding has different number of invitations:
            Random random = new Random();
            int randomIndex = random.nextInt(nameList.size()/2) + 1;

            for (int i = randomIndex; i < nameList.size()-1; i++) {
                // Create invitation:
                String emailAddress = Normalizer.normalize(nameList.get(i), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s+", "").toLowerCase();
                Invitation invitation = new Invitation(0, nameList.get(i), emailAddress + "@email.com", false, "", null, null);
                System.out.println("Creating invitation of " + nameList.get(i) + "for wedding with ID: " + wedding.getId());
                System.out.println("Invitation created with id: " + invitation.getId());
                invitation = invitationRepository.save(invitation);
                invitation.setWedding(wedding);
                wedding.getInvitations().add(invitation);
            }
            // Persist wedding:
            weddingRepository.save(wedding);
        });
    }

    @Test
    @Order(6)
    void crearGifts() {

        List<Wedding> weddings = weddingRepository.findAll();
        String[] giftsArray = {
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

        weddings.forEach(wedding -> {
            // Iniatialize collection:
            UtilLazy.initializeLazyOneToManyByJoinFetch(
                    entityManager,
                    Wedding.class,
                    Gift.class,
                    wedding.getId(),
                    wedding::setGifts);

            // Shuffle gift collection:
            List<String> giftsList = Arrays.asList(giftsArray);
            Collections.shuffle(giftsList);

            // Create Ramdon so every wedding has different number of gifts:
            Random random = new Random();
            int randomIndex = random.nextInt(giftsList.size()/2) + 1;

            for (int i = randomIndex; i < giftsList.size()-1; i++) {
                // Create gift:
                Gift regalo = new Gift(0, giftsList.get(i), false, null);
                regalo = giftRepository.save(regalo);
                regalo.setWedding(wedding);
                wedding.getGifts().add(regalo);
            }
            // Persist wedding:
            weddingRepository.save(wedding);
        });
    }

    @Test
    @Order(7)
    void crearTasks() {
        List<Wedding> weddings = weddingRepository.findAll();

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(0, "Definir la lista de invitados", LocalDate.of(2024, 2, 15), false, wedding1));
        taskList.add(new Task(0, "Reservar la ceremonia y el lugar de la recepción", LocalDate.of(2024, 8, 20), false, wedding1));
        taskList.add(new Task(0, "Fijar un presupuesto", LocalDate.of(2024, 10, 5), false, wedding1));
        taskList.add(new Task(0, "Infórmarse sobre los trámites necesarios", LocalDate.of(2024, 12, 30), false, wedding1));
        taskList.add(new Task(0, "Seleccionar padrinos y damas de honor", LocalDate.of(2025, 1, 15), false, wedding1));
        taskList.add(new Task(0, "Elegir los anillos de matrimonio", LocalDate.of(2025, 3, 20), false, wedding1));
        taskList.add(new Task(0, "Centros de mesa", LocalDate.of(2025, 4, 8), false, wedding1));
        taskList.add(new Task(0, "elegir catering", LocalDate.of(2025, 4, 22), false, wedding1));
        taskList.add(new Task(0, "Contratar fotógrafo", LocalDate.of(2025, 5, 1), false, wedding1));
        taskList.add(new Task(0, "Contratar dj", LocalDate.of(2025, 5, 9), false, wedding1));
        taskList.add(new Task(0, "Ramo y flores de decoración", LocalDate.of(2025, 6, 22), false, wedding1));
        taskList.add(new Task(0, "Planificar transporte", LocalDate.of(2025, 9, 15), false, wedding1));
        taskList.add(new Task(0, "Diseñar invitaciones", LocalDate.of(2025, 9, 1), false, wedding1));
        taskList.add(new Task(0, "Traje novio", LocalDate.of(2025, 10, 17), false, wedding1));
        taskList.add(new Task(0, "Vestido novia", LocalDate.of(2025, 12, 6), false, wedding1));
        taskList.add(new Task(0, "Peluquería", LocalDate.of(2026, 5, 2), false, wedding1));
        taskList.add(new Task(0, "Enviar invitaciones", LocalDate.of(2025, 10, 1), false, wedding1));
        taskList.add(new Task(0, "Hacer la lista de regalos", LocalDate.of(2025, 8, 20), false, wedding1));
        taskList.add(new Task(0, "Organizar despedida de soltero/soltera", LocalDate.of(2026, 2, 10), false, wedding1));
        taskList.add(new Task(0, "Elegir música para la ceremonia", LocalDate.of(2026, 1, 15), false, wedding1));
        taskList.add(new Task(0, "Probar el menú de la recepción", LocalDate.of(2025, 12, 1), false, wedding1));
        taskList.add(new Task(0, "Reservar alojamiento para invitados", LocalDate.of(2025, 11, 10), false, wedding1));
        taskList.add(new Task(0, "Coordinar el ensayo de la boda", LocalDate.of(2026, 4, 15), false, wedding1));
        taskList.add(new Task(0, "Comprar accesorios de novia", LocalDate.of(2026, 3, 20), false, wedding1));
        taskList.add(new Task(0, "Seleccionar y confirmar proveedores", LocalDate.of(2026, 1, 5), false, wedding1));
        taskList.add(new Task(0, "Planificar la luna de miel", LocalDate.of(2026, 2, 25), false, wedding1));
        taskList.add(new Task(0, "Comprar zapatos de boda", LocalDate.of(2026, 3, 5), false, wedding1));
        taskList.add(new Task(0, "Organizar el seating plan", LocalDate.of(2026, 4, 10), false, wedding1));
        taskList.add(new Task(0, "Reunirse con el oficiante de la ceremonia", LocalDate.of(2026, 3, 10), false, wedding1));
        taskList.add(new Task(0, "Confirmar detalles con el florista", LocalDate.of(2026, 4, 1), false, wedding1));
        taskList.add(new Task(0, "Hacer pruebas de maquillaje", LocalDate.of(2026, 3, 25), false, wedding1));

        weddings.forEach(wedding -> {
            // Iniatialize collection:
            UtilLazy.initializeLazyOneToManyByJoinFetch(
                    entityManager,
                    Wedding.class,
                    Task.class,
                    wedding.getId(),
                    wedding::setTasks);
            // Shuffle gift collection:
            Collections.shuffle(taskList);

            // Create Ramdon so every wedding has different number of gifts:
            Random random = new Random();
            int randomIndex = random.nextInt(taskList.size()/2) + 1;

            for (int i = randomIndex; i < taskList.size()-1; i++) {
                taskList.get(i).setId(0L);
                taskList.get(i).setWedding(null);
                Task newTask = taskRepository.saveAndFlush(taskList.get(i));
                newTask.setWedding(wedding);
                wedding.getTasks().add(newTask);
            }
            // Persist wedding:
            weddingRepository.save(wedding);
        });
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
