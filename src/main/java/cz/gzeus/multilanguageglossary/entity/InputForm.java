package cz.gzeus.multilanguageglossary.entity;

import lombok.Data;

@Data
public class InputForm {

    private String text;

    private int languageId;

    private int wordId;
}
