package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Regalo;
import org.iesvdm.api_rest.service.RegaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/regalos")
public class RegaloController {

    @Autowired
    private RegaloService regaloService;

    @GetMapping(value = {"", "/"})
    public List<Regalo> all() {
        return regaloService.all();
    }

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Gifts");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.regaloService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Gifts");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.regaloService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Regalo one(@PathVariable("id") Long id) {
        return regaloService.one(id);
    }

    @PostMapping({"","/"})
    public Regalo newRegalo(@RequestBody Regalo regalo) {
        log.info("Creando un regalo = " + regalo);
        return this.regaloService.save(regalo);
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
