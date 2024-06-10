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

    /**
     * Receives user id and sends an email confirmation to his/her email address
     * @param id
     */
    @PostMapping(value = {"confirmation","confirmation/"}, params = {"id"})
    public void sendConfirmation(@RequestParam Long id) {

        this.mailSenderService.sendConfirmation(id);
        log.info("Confirmation message processing in backend!");
    }

    /**
     * Receives invitation id and sends an invitation email to guest`s email address
     * @param id
     */
    @PostMapping(value = {"invitation","invitation/"}, params = {"id"})
    public void sendInvitation(@RequestParam Long id) {

        log.info("Invitation id received in backend to be sent");
        this.mailSenderService.sendInvitation(id);
        log.info("Invitation message processing in backend!");
    }

    /**
     * Receives invitation id and sends an email notification to the wedding owner
     * @param id
     */
    @PostMapping(value = {"notification","notification/"}, params = {"id"})
    public void notifyAcceptation(@RequestParam Long id){

        log.info("Notification of accepted invitation being processed in backend!");
        this.mailSenderService.sendNotification(id);
    }
}
