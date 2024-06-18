package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.Invitation;
import org.iesvdm.api_rest.dto.InvitationDTO;
import org.iesvdm.api_rest.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1/api/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @GetMapping(value = {"", "/"}, params = {"!id",})
    public List<Invitation> all() {
        log.info("Accessing ALL Invitations");
        return invitationService.all();
    }

    @GetMapping(value = {"","/"}, params = {"id", "page", "size", "!filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size){
        log.info("Accessing paginated Invitations by Wedding ID");
        log.info("PAGE: {} & SIZE: {}", page, size);

        Map<String, Object> responseAll = this.invitationService.allByWeddingId(id, page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = { "", "/" }, params = {"id", "page", "size", "filter"})
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam String filter) {
        log.info("Accessing paginated and filtered Invitations by Wedding ID");
        log.info("PAGE: " + page + " & SIZE: " + size + " & Filtered by: " + filter);

        Map<String, Object> responseAll = this.invitationService.findByWeddingIdAndFilter(id, page, size, filter);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping( "/{id}")
    public InvitationDTO one(@PathVariable("id") Long id) {
        return invitationService.oneMapped(id);
    }

    @PostMapping(value = { "", "/" }, params = {"id"})
    public Invitation newInvitation(
            @RequestParam Long id,
            @RequestBody InvitationDTO invitation) {
        log.info("Creando un invitation = " + invitation + " para la boda con id " + id);
        return this.invitationService.save(id, invitation);
    }

    @PutMapping("/{id}")
    public Invitation replaceInvitation(@PathVariable("id") Long id, @RequestBody InvitationDTO invitation) {
        log.info("Updating invitation con id = " + id + "\n invitation = " + invitation);
        return this.invitationService.replace(id, invitation);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteInvitation(@PathVariable("id") Long id) {
        this.invitationService.delete(id);
    }
}
