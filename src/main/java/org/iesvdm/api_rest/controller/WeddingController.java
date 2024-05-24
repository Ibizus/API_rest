package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.service.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping( "/{id}")
    public Wedding one(@PathVariable("id") Long id) {
        return weddingService.one(id);
    }

    @GetMapping(value = {"","/"}, params = "user")
    public Page<Wedding> findByUser(@RequestParam Long user){
        return this.weddingService.findByUser(user);
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
