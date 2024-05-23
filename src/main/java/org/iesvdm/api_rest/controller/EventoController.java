package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Evento;
import org.iesvdm.api_rest.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping(value = {"", "/"})
    public List<Evento> all() {
        return eventoService.all();
    }

    @PostMapping({"","/"})
    public Evento newEvento(@RequestBody Evento evento) {
        log.info("Creando un evento = " + evento);
        return this.eventoService.save(evento);
    }

    @GetMapping( "/{id}")
    public Evento one(@PathVariable("id") Long id) {
        return eventoService.one(id);
    }

    @PutMapping("/{id}")
    public Evento replaceEvento(@PathVariable("id") Long id, @RequestBody Evento evento) {
        log.info("Actualizar evento con id = " + id + "\n evento = " + evento);
        return this.eventoService.replace(id, evento);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable("id") Long id) {
        this.eventoService.delete(id);
    }
}
