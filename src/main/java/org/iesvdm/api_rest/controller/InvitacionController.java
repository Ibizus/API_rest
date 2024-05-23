package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.service.InvitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/invitaciones")
public class InvitacionController {

    @Autowired
    private InvitacionService invitacionService;

    @GetMapping(value = {"", "/"})
    public List<Invitacion> all() {
        return invitacionService.all();
    }

    @PostMapping({"","/"})
    public Invitacion newInvitacion(@RequestBody Invitacion invitacion) {
        log.info("Creando un invitacion = " + invitacion);
        return this.invitacionService.save(invitacion);
    }

    @GetMapping( "/{id}")
    public Invitacion one(@PathVariable("id") Long id) {
        return invitacionService.one(id);
    }

    @PutMapping("/{id}")
    public Invitacion replaceInvitacion(@PathVariable("id") Long id, @RequestBody Invitacion invitacion) {
        log.info("Actualizar invitacion con id = " + id + "\n invitacion = " + invitacion);
        return this.invitacionService.replace(id, invitacion);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteInvitacion(@PathVariable("id") Long id) {
        this.invitacionService.delete(id);
    }
}
