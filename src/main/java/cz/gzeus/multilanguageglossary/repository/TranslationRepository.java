package cz.gzeus.multilanguageglossary.repository;

import cz.gzeus.multilanguageglossary.entity.Language;
import cz.gzeus.multilanguageglossary.entity.Translation;
import cz.gzeus.multilanguageglossary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Integer> {


    List<Translation> findByTextContaining(String text);

    List<Translation> findByLanguage(Language language);

    List<Translation> findByWord(Word word);


}
