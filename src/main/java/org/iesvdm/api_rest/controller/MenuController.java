package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = {"", "/"})
    public List<Menu> all() {
        return menuService.all();
    }

    @GetMapping(value = {"","/"}, params = {"id", "page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Menus by Wedding ID");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.menuService.allByWeddingId(id, page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = {"id", "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Menus by Wedding ID");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.menuService.findByWeddingIdAndFilter(id, page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public Menu one(@PathVariable("id") Long id) {
        return menuService.one(id);
    }

    @PostMapping(value = { "", "/" }, params = {"id"})
    public Menu newMenu(
            @RequestParam Long id,
            @RequestBody Menu menu) {
        log.info("Creando un menu = " + menu + " para la boda con id " + id);
        return this.menuService.save(id, menu);
    }

    @PutMapping("/{id}")
    public Menu replaceMenu(@PathVariable("id") Long id, @RequestBody Menu menu) {
        log.info("Actualizar menu con id = " + id + "\n menu = " + menu);
        return this.menuService.replace(id, menu);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable("id") Long id) {
        this.menuService.delete(id);
    }
}
