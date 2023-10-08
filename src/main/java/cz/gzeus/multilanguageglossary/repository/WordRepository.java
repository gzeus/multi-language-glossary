package cz.gzeus.multilanguageglossary.repository;

import cz.gzeus.multilanguageglossary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {




}
