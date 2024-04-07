package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Invitado;
import org.iesvdm.api_rest.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @GetMapping(value = {"", "/"})
    public List<Invitado> all() {
        return invitadoService.all();
    }

    @PostMapping({"","/"})
    public Invitado newInvitado(@RequestBody Invitado invitado) {
        log.info("Creando un invitado = " + invitado);
        return this.invitadoService.save(invitado);
    }

    @GetMapping( "/{id}")
    public Invitado one(@PathVariable("id") Long id) {
        return invitadoService.one(id);
    }

    @PutMapping("/{id}")
    public Invitado replaceInvitado(@PathVariable("id") Long id, @RequestBody Invitado invitado) {
        log.info("Actualizar invitado con id = " + id + "\n invitado = " + invitado);
        return this.invitadoService.replace(id, invitado);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteInvitado(@PathVariable("id") Long id) {
        this.invitadoService.delete(id);
    }
}
