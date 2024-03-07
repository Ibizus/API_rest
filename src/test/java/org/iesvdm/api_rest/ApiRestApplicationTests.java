package org.iesvdm.api_rest;

import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.repository.BodaRepository;
import org.iesvdm.api_rest.repository.MenuRepository;
import org.iesvdm.api_rest.repository.UsuarioRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    @Order(1)
    void contextLoads() {
    }


    @Test
    @Order(2)
    void crearUsuarios(){

        admin = new Usuario(0, "Hector", "Lopez", "Diaz", "calle Veleta 2", "", "29651", "Mijas", "Malaga", "hlopdia699@g.educaand.es","665661519", new HashSet<>());
        user = new Usuario(0, "Alvaro", "Moreno", "Barreiro", "calle de la Luz 25", "", "29640", "Fuengirola", "Malaga", "alvaro@educaand.es","666666666", new HashSet<>());



    }

}
