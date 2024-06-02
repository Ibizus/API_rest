package org.iesvdm.api_rest.controller;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.api_rest.domain.FAQ;
import org.iesvdm.api_rest.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1/api/faqs")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @GetMapping(value = {"", "/"})
    public List<FAQ> all() {
        return faqService.all();
    }

}
