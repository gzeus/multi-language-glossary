package cz.gzeus.multilanguageglossary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "word")
@Data
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    private String text;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "word_translation",
            inverseJoinColumns = @JoinColumn(name = "translation_id"),
            joinColumns = @JoinColumn(name = "word_id")
    )
    private List<Translation> translations;


}
