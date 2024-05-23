package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"", "/"})
    public List<Usuario> all() {
        return usuarioService.all();
    }

    @GetMapping(value = {"", "/"}, params = "nombre")
    public Page<Usuario> findByName(@RequestParam("nombre") String cadena){
        return usuarioService.findByName(cadena);
    }

    @GetMapping(value = {"", "/"}, params = "apellido")
    public Page<Usuario> findByApellido(@RequestParam("apellido") String cadena){
        return usuarioService.findByApellido(cadena);
    }

    @PostMapping({"","/"})
    public Usuario newUsuario(@RequestBody Usuario usuario) {
        log.info("Creando un usuario = " + usuario);
        return this.usuarioService.save(usuario);
    }

    @GetMapping( "/{id}")
    public Usuario one(@PathVariable("id") Long id) {
        return usuarioService.one(id);
    }

    @PutMapping("/{id}")
    public Usuario replaceUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        log.info("Actualizar usuario con id = " + id + "\n usuario = " + usuario);
        return this.usuarioService.replace(id, usuario);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        this.usuarioService.delete(id);
    }
}
