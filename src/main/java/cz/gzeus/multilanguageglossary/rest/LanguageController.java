package cz.gzeus.multilanguageglossary.rest;

import cz.gzeus.multilanguageglossary.entity.InputForm;
import cz.gzeus.multilanguageglossary.entity.Language;
import cz.gzeus.multilanguageglossary.entity.Translation;
import cz.gzeus.multilanguageglossary.repository.LanguageRepository;
import cz.gzeus.multilanguageglossary.repository.TranslationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/languages")
public class LanguageController {


    @Autowired
    private final LanguageRepository languageRepository;

    @Autowired
    private final TranslationRepository translationRepository;

    public LanguageController(LanguageRepository languageRepository, TranslationRepository translationRepository) {
        this.languageRepository = languageRepository;
        this.translationRepository = translationRepository;
    }

    @GetMapping("/manage")
    public String manageLanguages(Model model){

        List<Language> languages = languageRepository.findAll();

        languages.sort(Comparator.comparing(Language::getId));

        model.addAttribute("languages", languages);
        model.addAttribute("inputForm", new InputForm());

        return "language-manager";
    }

    @GetMapping(path = "/delete")
    public String deleteLanguage(@RequestParam("languageId") int id){


        Optional<Language> foundLanguage  = languageRepository.findById(id);
        Language languageToDelete;
        List<Translation> translationsToDelete;

        if (foundLanguage.isPresent()){
            languageToDelete = foundLanguage.get();
            translationsToDelete = translationRepository.findByLanguage(languageToDelete);

            translationsToDelete.forEach(translationRepository::delete);
            languageRepository.delete(languageToDelete);


        }

        return "redirect:/languages/manage";

    }

    @PostMapping(path = "/save")
    public String saveNewLanguage(@Valid @ModelAttribute("inputForm") InputForm newLanguage, BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            return "redirect:/languages/manage";
        }

        Language tempLanguage = new Language();
        tempLanguage.setName(newLanguage.getText());
        languageRepository.save(tempLanguage);


        return "redirect:/languages/manage";


    }




}
