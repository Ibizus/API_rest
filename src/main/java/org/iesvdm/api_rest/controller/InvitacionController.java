package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Invitacion;
import org.iesvdm.api_rest.service.InvitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Gifts");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.invitacionService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Gifts");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.invitacionService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Invitacion one(@PathVariable("id") Long id) {
        return invitacionService.one(id);
    }

    @PostMapping({"","/"})
    public Invitacion newInvitacion(@RequestBody Invitacion invitacion) {
        log.info("Creando un invitacion = " + invitacion);
        return this.invitacionService.save(invitacion);
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
