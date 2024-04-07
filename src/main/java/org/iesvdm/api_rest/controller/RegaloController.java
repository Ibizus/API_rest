package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Regalo;
import org.iesvdm.api_rest.service.RegaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/regalos")
public class RegaloController {

    @Autowired
    private RegaloService regaloService;

    @GetMapping(value = {"", "/"})
    public List<Regalo> all() {
        return regaloService.all();
    }

    @PostMapping({"","/"})
    public Regalo newRegalo(@RequestBody Regalo regalo) {
        log.info("Creando un regalo = " + regalo);
        return this.regaloService.save(regalo);
    }

    @GetMapping( "/{id}")
    public Regalo one(@PathVariable("id") Long id) {
        return regaloService.one(id);
    }

    @PutMapping("/{id}")
    public Regalo replaceRegalo(@PathVariable("id") Long id, @RequestBody Regalo regalo) {
        log.info("Actualizar regalo con id = " + id + "\n regalo = " + regalo);
        return this.regaloService.replace(id, regalo);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRegalo(@PathVariable("id") Long id) {
        this.regaloService.delete(id);
    }
}
