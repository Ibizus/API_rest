package org.iesvdm.api_rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@CrossOrigin(origins = {"*"})
@Controller
@RequestMapping("/data-api")
public class DataApiController {

    @GetMapping({"", "/"})
    public RedirectView openApi(){
        return new RedirectView("http://localhost:8080/swagger-ui/index.html");
    }

    @GetMapping({"/explorer"})
    public RedirectView halExplorer(){
        return new RedirectView("http://localhost:8080/data-api/explorer/index.html");
    }

}
