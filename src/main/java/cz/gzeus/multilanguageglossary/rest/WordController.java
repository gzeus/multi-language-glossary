package cz.gzeus.multilanguageglossary.rest;

import cz.gzeus.multilanguageglossary.entity.Language;
import cz.gzeus.multilanguageglossary.entity.Translation;
import cz.gzeus.multilanguageglossary.entity.Word;
import cz.gzeus.multilanguageglossary.repository.LanguageRepository;
import cz.gzeus.multilanguageglossary.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/words")
public class WordController {

    private WordRepository wordRepository;
    private LanguageRepository languageRepository;


    public WordController(WordRepository wordRepository, LanguageRepository languageRepository){

        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
    }

    @GetMapping("/list")
    public String listWords(Model model) {

        List<Word> wordList = wordRepository.findAll();
        List<Language> languageList = languageRepository.findAll();
        Translation translation = new Translation();
        int counter = 0;

        languageList.sort(Comparator.comparing(Language::getId));

        model.addAttribute("words", wordList);
        model.addAttribute("languages", languageList);
        model.addAttribute("translation", translation);

        return "words-list";
    }
}
