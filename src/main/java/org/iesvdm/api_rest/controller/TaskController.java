package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Task;
import org.iesvdm.api_rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = {"", "/"}, params = {"!id",})
    public List<Task> all() {
        return taskService.all();
    }

    @GetMapping(value = {"","/"}, params = {"id", "page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Tasks by Wedding ID");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.taskService.allByWeddingId(id, page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = {"id", "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Tasks by Wedding ID");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.taskService.findByWeddingIdAndFilter(id, page, size, filter);
        return ResponseEntity.ok(responseAll);
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
