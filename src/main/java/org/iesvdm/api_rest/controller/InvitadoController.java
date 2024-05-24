package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Invitado;
import org.iesvdm.api_rest.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @GetMapping(value = {"", "/"})
    public List<Invitado> all() {
        return invitadoService.all();
    }

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Gifts");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.invitadoService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Gifts");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.invitadoService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Invitado one(@PathVariable("id") Long id) {
        return invitadoService.one(id);
    }

    @PostMapping({"","/"})
    public Invitado newInvitado(@RequestBody Invitado invitado) {
        log.info("Creando un invitado = " + invitado);
        return this.invitadoService.save(invitado);
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
