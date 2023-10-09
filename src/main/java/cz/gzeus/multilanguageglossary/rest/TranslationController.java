package cz.gzeus.multilanguageglossary.rest;

import cz.gzeus.multilanguageglossary.entity.Translation;
import cz.gzeus.multilanguageglossary.repository.LanguageRepository;
import cz.gzeus.multilanguageglossary.repository.TranslationRepository;
import cz.gzeus.multilanguageglossary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/translations")
public class TranslationController {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private TranslationRepository translationRepository;

    public TranslationController() {
    }

    public TranslationController(WordRepository wordRepository, LanguageRepository languageRepository, TranslationRepository translationRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.translationRepository = translationRepository;
    }

    @GetMapping(path = "/delete")
    public String deleteTranslation(@RequestParam("translationId") int id){


        translationRepository.deleteById(id);


        return "redirect:/words/list";


    }

    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String saveTranslation2(@RequestParam Map<String, String> request){

        System.out.println(request);
        Translation tempTranslation = new Translation();

        tempTranslation.setText(request.get("text"));
        tempTranslation.setWordId(Integer.parseInt(request.get("wordId")));
        tempTranslation.setLangId(Integer.parseInt(request.get("languageId")));

        System.out.println(tempTranslation);


        var tempWord= wordRepository.findById(Integer.parseInt(request.get("wordId")));
        tempWord.ifPresent(tempTranslation::setWord);
        tempWord.ifPresent(System.out::println);

        var tempLanguage = languageRepository.findById(Integer.parseInt(request.get("languageId")));
        tempLanguage.ifPresent(tempTranslation::setLanguage);
        tempLanguage.ifPresent(System.out::println);

        System.out.println(tempTranslation);
        translationRepository.save(tempTranslation);
        System.out.println("saved!");

        return "redirect:/words/list#wordId" + tempTranslation.getWordId();


    }
}
