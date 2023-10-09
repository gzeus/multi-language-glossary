package cz.gzeus.multilanguageglossary.rest;

import cz.gzeus.multilanguageglossary.entity.InputForm;
import cz.gzeus.multilanguageglossary.entity.Language;
import cz.gzeus.multilanguageglossary.entity.Translation;
import cz.gzeus.multilanguageglossary.entity.Word;
import cz.gzeus.multilanguageglossary.repository.LanguageRepository;
import cz.gzeus.multilanguageglossary.repository.TranslationRepository;
import cz.gzeus.multilanguageglossary.repository.WordRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/words")
public class WordController {

    private WordRepository wordRepository;
    private LanguageRepository languageRepository;

    private TranslationRepository translationRepository;


    public WordController(WordRepository wordRepository, LanguageRepository languageRepository, TranslationRepository translationRepository){

        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.translationRepository = translationRepository;
    }

    @PostMapping(path = "/save")
    public String saveNewBaseWord(@Valid @ModelAttribute("inputForm") InputForm newWord, BindingResult bindingResult){


        if (bindingResult.hasErrors()){
             return "redirect:/words/list";
        }

        Word tempWord = new Word();
        tempWord.setText(newWord.getText());
        wordRepository.save(tempWord);


        return "redirect:/words/list#wordId" + tempWord.getId();


    }

    @GetMapping(path = "/delete")
    public String deleteLanguage(@RequestParam("wordId") int id){


        Optional<Word> foundWord = wordRepository.findById(id);
        Word wordToDelete;
        List<Translation> translationsToDelete;

        if (foundWord.isPresent()){
            wordToDelete = foundWord.get();
            translationsToDelete = translationRepository.findByWord(wordToDelete);

            translationsToDelete.forEach(translationRepository::delete);
            wordRepository.delete(wordToDelete);


        }

        return "redirect:/words/list";

    }

    @GetMapping("/search")
    public String searchForWords(@RequestParam("searchTerm") String searchTerm, Model model) {

        // get list of base words similar to the input
        List<Word> wordList = wordRepository.findByTextContaining(searchTerm);
        // get list of all translations similar to output and add their base words to the list
        List<Translation> translationList = translationRepository.findByTextContaining(searchTerm);
        translationList.forEach(s -> wordList.add(s.getWord()));

        List<Language> languageList = languageRepository.findAll();
        Translation translation = new Translation();

        languageList.sort(Comparator.comparing(Language::getId));

        model.addAttribute("words", wordList);
        model.addAttribute("languages", languageList);
        model.addAttribute("translation", translation);
        model.addAttribute("inputForm", new InputForm());


        return "words-list";
    }

    @GetMapping("/list")
    public String listWords(Model model) {

        List<Word> wordList = wordRepository.findAll();
        List<Language> languageList = languageRepository.findAll();
        Translation translation = new Translation();
        InputForm inputForm = new InputForm();



        languageList.sort(Comparator.comparing(Language::getId));

        model.addAttribute("words", wordList);
        model.addAttribute("languages", languageList);
        model.addAttribute("translation", translation);
        model.addAttribute("inputForm", inputForm);


        return "words-list";
    }
}
