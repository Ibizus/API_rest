package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.User;
import org.iesvdm.api_rest.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
@RequestMapping("/v1/api/send/")
public class  MailSenderController {

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping(value = {"invitation","invitation/"}, params = {"id"})
    public void sendInvitation(@RequestParam Long id) {

        log.info("Invitation id received in backend to be sent");
        this.mailSenderService.sendInvitation(id);
        log.info("Invitation message processing in backend!");
    }

    @PostMapping(value = {"confirmation","confirmation/"}, params = {"!id"})
    public void sendConfirmation(@RequestBody User user) {

        this.mailSenderService.sendConfirmation(user);
        log.info("Confirmation message processing in backend!");
    }

    @PostMapping(value = {"accepted","accepted/"}, params = {"id"})
    public void notifyAcceptation(@RequestParam Long id){

        log.info("Notification of accepted invitation being processed in backend!");
        this.mailSenderService.sendNotification(id);
    }
}
