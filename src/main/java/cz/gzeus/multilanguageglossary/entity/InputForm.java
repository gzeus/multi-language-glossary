package cz.gzeus.multilanguageglossary.entity;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InputForm {


    @Nonnull
    @NotBlank(message = "Cannot be empty")
    private String text;

}
