package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.User;
import org.iesvdm.api_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public List<User> all() {
        return userService.all();
    }

    @GetMapping(value = {"", "/"}, params = "name")
    public Page<User> findByName(
            @RequestParam("name") String filter){
        return userService.findByName(filter);
    }

    @GetMapping(value = {"", "/"}, params = "lastname")
    public Page<User> findByLastname(
            @RequestParam("lastname") String filter){
        return userService.findByLastname(filter);
    }

    @GetMapping(value = {"","/"}, params = {"page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Users");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.userService.all(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = { "page", "size","filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Users");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.userService.findByFilter(page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    // Users created through AuthController
//    @PostMapping({"","/"})
//    public User newUser(@RequestBody User user) {
//        log.info("Creating an user = {}", user);
//        return this.userService.save(user);
//    }

    @GetMapping( "/{id}")
    public User one(@PathVariable("id") Long id) {
        return userService.one(id);
    }

    @PutMapping("/{id}")
    public User replaceUser(@PathVariable("id") Long id, @RequestBody User user) {
        log.info("Actualizar user con id = " + id + "\n user = " + user);
        return this.userService.replace(id, user);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.delete(id);
    }
}
