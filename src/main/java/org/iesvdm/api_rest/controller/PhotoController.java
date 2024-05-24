package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Photo;
import org.iesvdm.api_rest.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping(value = {"", "/"})
    public List<Photo> all() {
        return photoService.all();
    }

    @PostMapping({"","/"})
    public Photo newPhoto(@RequestBody Photo photo) {
        log.info("Creando un photo = " + photo);
        return this.photoService.save(photo);
    }

    @GetMapping( "/{id}")
    public Photo one(@PathVariable("id") Long id) {
        return photoService.one(id);
    }

    @PutMapping("/{id}")
    public Photo replacePhoto(@PathVariable("id") Long id, @RequestBody Photo photo) {
        log.info("Actualizar photo con id = " + id + "\n photo = " + photo);
        return this.photoService.replace(id, photo);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable("id") Long id) {
        this.photoService.delete(id);
    }
}
