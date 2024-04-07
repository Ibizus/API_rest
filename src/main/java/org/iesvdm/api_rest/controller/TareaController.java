package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Tarea;
import org.iesvdm.api_rest.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping(value = {"", "/"})
    public List<Tarea> all() {
        return tareaService.all();
    }

    @PostMapping({"","/"})
    public Tarea newTarea(@RequestBody Tarea tarea) {
        log.info("Creando un tarea = " + tarea);
        return this.tareaService.save(tarea);
    }

    @GetMapping( "/{id}")
    public Tarea one(@PathVariable("id") Long id) {
        return tareaService.one(id);
    }

    @PutMapping("/{id}")
    public Tarea replaceTarea(@PathVariable("id") Long id, @RequestBody Tarea tarea) {
        log.info("Actualizar tarea con id = " + id + "\n tarea = " + tarea);
        return this.tareaService.replace(id, tarea);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTarea(@PathVariable("id") Long id) {
        this.tareaService.delete(id);
    }
}
