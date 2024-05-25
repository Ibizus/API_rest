package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Task;
import org.iesvdm.api_rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = {"", "/"})
    public List<Task> all() {
        return taskService.all();
    }

    @PostMapping({"","/"})
    public Task newTask(@RequestBody Task task) {
        log.info("Creando un task = " + task);
        return this.taskService.save(task);
    }

    @GetMapping( "/{id}")
    public Task one(@PathVariable("id") Long id) {
        return taskService.one(id);
    }

    @PutMapping("/{id}")
    public Task replaceTask(@PathVariable("id") Long id, @RequestBody Task task) {
        log.info("Actualizar task con id = " + id + "\n task = " + task);
        return this.taskService.replace(id, task);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        this.taskService.delete(id);
    }
}
