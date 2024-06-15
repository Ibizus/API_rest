package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.service.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/weddings")
public class WeddingController {

    @Autowired
    private WeddingService weddingService;

    @GetMapping({"", "/"})
    public List<Wedding> all() {
        return weddingService.all();
    }

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Weddings");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.weddingService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Weddings");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.weddingService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Wedding one(@PathVariable("id") Long id) {
        return weddingService.one(id);
    }

    @GetMapping(value = {"","/"}, params = "user")
    public ResponseEntity<Map<String, Object>> findByUser(@RequestParam Long user){
        Map<String, Object> responseAll = this.weddingService.findByUser(user);
        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"","/"})
    public Wedding newWedding(@RequestBody Wedding wedding) {
        log.info("Creating wedding = " + wedding);
        return this.weddingService.save(wedding);
    }

    @PutMapping("/{id}")
    public Wedding replaceWedding(@PathVariable("id") Long id, @RequestBody Wedding wedding) {
        log.info("Updating wedding with id = " + id + "\n wedding = " + wedding);
        return this.weddingService.replace(id, wedding);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteWedding(@PathVariable("id") Long id) {
        this.weddingService.delete(id);
    }
}
