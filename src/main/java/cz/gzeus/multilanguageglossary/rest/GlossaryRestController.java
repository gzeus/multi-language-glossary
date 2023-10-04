package cz.gzeus.multilanguageglossary.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlossaryRestController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }
}
