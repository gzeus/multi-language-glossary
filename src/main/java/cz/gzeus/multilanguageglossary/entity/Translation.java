package cz.gzeus.multilanguageglossary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "translation")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "word_translation",
            joinColumns = @JoinColumn(name = "translation_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Word word;

    @JsonInclude()
    @Transient
    private int langId;

    @JsonInclude()
    @Transient
    private int wordId;

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", word=" + word +
                ", langId=" + langId +
                ", wordId=" + wordId +
                '}';
    }
}
