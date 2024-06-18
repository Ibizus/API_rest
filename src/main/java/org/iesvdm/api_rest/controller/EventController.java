package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Event;
import org.iesvdm.api_rest.domain.Event;
import org.iesvdm.api_rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = {"", "/"})
    public List<Event> all() {
        return eventService.all();
    }

    @GetMapping(value = {"","/"}, params = {"id"})
    public List<Event> all(
            @RequestParam Long id){
        log.info("Accessing all events by Wedding ID");
        return this.eventService.allByWeddingId(id);
    }

    @GetMapping( "/{id}")
    public Event one(@PathVariable("id") Long id) {
        return eventService.one(id);
    }

    @PostMapping(value = { "", "/" }, params = {"id"})
    public Event newEvent(
            @RequestParam Long id,
            @RequestBody Event event) {
        log.info("Creando un event = " + event + " para la boda con id " + id);
        return this.eventService.save(id, event);
    }

    @PutMapping("/{id}")
    public Event replaceEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        log.info("updating event with id = " + id + "\n event = " + event);
        return this.eventService.replace(id, event);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        this.eventService.delete(id);
    }
}
