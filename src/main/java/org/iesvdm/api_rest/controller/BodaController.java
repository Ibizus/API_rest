package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Boda;
import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.service.BodaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/bodas")
public class BodaController {

    @Autowired
    private BodaService bodaService;

    @GetMapping({"", "/"})
    public List<Boda> all() {
        return bodaService.all();
    }

    @GetMapping( "/{id}")
    public Boda one(@PathVariable("id") Long id) {
        return bodaService.one(id);
    }

    @GetMapping(value = {"","/"}, params = "user")
    public Boda findByUser(@RequestParam Long user){
        return this.bodaService.findByUser(user);
    }

    @PostMapping({"","/"})
    public Boda newBoda(@RequestBody Boda boda) {
        log.info("Creando una boda = " + boda);
        return this.bodaService.save(boda);
    }

    @PutMapping("/{id}")
    public Boda replaceBoda(@PathVariable("id") Long id, @RequestBody Boda boda) {
        log.info("Actualizar boda con id = " + id + "\n boda = " + boda);
        return this.bodaService.replace(id, boda);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBoda(@PathVariable("id") Long id) {
        this.bodaService.delete(id);
    }
}
