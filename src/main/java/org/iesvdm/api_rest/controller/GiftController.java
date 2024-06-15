package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Gift;
import org.iesvdm.api_rest.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/gifts")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @GetMapping(value = {"", "/"}, params = {"!id",})
    public List<Gift> all() {
        return giftService.all();
    }

    @GetMapping(value = {"","/"}, params = {"id", "page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Gifts by Wedding ID");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.giftService.allByWeddingId(id, page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = {"id", "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Gifts by Wedding ID");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.giftService.findByWeddingIdAndFilter(id, page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Gift one(@PathVariable("id") Long id) {
        return giftService.one(id);
    }

    @PostMapping(value = { "", "/" }, params = {"id"})
    public Gift newGift(
            @RequestParam Long id,
            @RequestBody Gift gift) {
        log.info("Creando un gift = " + gift + " para la boda con id " + id);
        return this.giftService.save(id, gift);
    }

    @PutMapping("/{id}")
    public Gift replaceGift(@PathVariable("id") Long id, @RequestBody Gift gift) {
        log.info("Actualizar gift con id = " + id + "\n gift = " + gift);
        return this.giftService.replace(id, gift);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable("id") Long id) {
        this.giftService.delete(id);
    }
}
