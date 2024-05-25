package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Event;
import org.iesvdm.api_rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = {"", "/"})
    public List<Event> all() {
        return eventService.all();
    }

    @PostMapping({"","/"})
    public Event newEvent(@RequestBody Event event) {
        log.info("Creating an event = " + event);
        return this.eventService.save(event);
    }

    @GetMapping( "/{id}")
    public Event one(@PathVariable("id") Long id) {
        return eventService.one(id);
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
