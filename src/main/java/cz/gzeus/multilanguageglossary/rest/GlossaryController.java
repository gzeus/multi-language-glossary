package cz.gzeus.multilanguageglossary.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


// Controller for ThymeLeaf templates serving
@Controller
public class GlossaryController {

    @GetMapping("/")
    public String helloWorld(Model model){

        model.addAttribute("theDate", new java.util.Date());
        return "helloworld";
    }


}
