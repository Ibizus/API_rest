package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Foto;
import org.iesvdm.api_rest.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/fotos")
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @GetMapping(value = {"", "/"})
    public List<Foto> all() {
        return fotoService.all();
    }

    @PostMapping({"","/"})
    public Foto newFoto(@RequestBody Foto foto) {
        log.info("Creando un foto = " + foto);
        return this.fotoService.save(foto);
    }

    @GetMapping( "/{id}")
    public Foto one(@PathVariable("id") Long id) {
        return fotoService.one(id);
    }

    @PutMapping("/{id}")
    public Foto replaceFoto(@PathVariable("id") Long id, @RequestBody Foto foto) {
        log.info("Actualizar foto con id = " + id + "\n foto = " + foto);
        return this.fotoService.replace(id, foto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFoto(@PathVariable("id") Long id) {
        this.fotoService.delete(id);
    }
}
