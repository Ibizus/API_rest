package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Menu;
import org.iesvdm.api_rest.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping({"", "/"})
    public List<Menu> all() {
        return menuService.all();
    }

    @GetMapping(value = {"","/"}, params = "filtrar")
    public List<Menu> all(@RequestParam("filtrar")Optional<String> filtrarOptional){

        log.info("Accediendo a menús con filtrado en todos los campos");
        return this.menuService.filter(filtrarOptional);
    }


    @PostMapping({"","/"})
    public Menu newMenu(@RequestBody Menu menu) {
        log.info("Creando un menu = " + menu);
        return this.menuService.save(menu);
    }

    @GetMapping( "/{id}")
    public Menu one(@PathVariable("id") Long id) {
        return menuService.one(id);
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
