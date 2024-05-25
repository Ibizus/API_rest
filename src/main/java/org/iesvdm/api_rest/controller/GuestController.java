package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Guest;
import org.iesvdm.api_rest.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping(value = {"", "/"})
    public List<Guest> all() {
        return guestService.all();
    }

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Guests");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.guestService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Guests");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.guestService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Guest one(@PathVariable("id") Long id) {
        return guestService.one(id);
    }

    @PostMapping({"","/"})
    public Guest newGuest(@RequestBody Guest guest) {
        log.info("creating a guest = " + guest);
        return this.guestService.save(guest);
    }

    @PutMapping("/{id}")
    public Guest replaceGuest(@PathVariable("id") Long id, @RequestBody Guest guest) {
        log.info("Updating guest with id = " + id + "\n guest = " + guest);
        return this.guestService.replace(id, guest);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable("id") Long id) {
        this.guestService.delete(id);
    }
}
