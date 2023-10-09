package cz.gzeus.multilanguageglossary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "word")
@Data
@RequiredArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Text may not be blank")
    @Column(name = "text")
    private String text;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "word_translation",
            inverseJoinColumns = @JoinColumn(name = "translation_id"),
            joinColumns = @JoinColumn(name = "word_id")
    )
    private List<Translation> translations;

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
