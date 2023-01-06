package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.dto.validator.IValidatorArticle;
import com.idforideas.wikideas.dto.validator.IValidatorTheme;
import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ThemeDTO {
    @NotBlank(groups= {IValidatorTheme.class})
    private Long idTheme;
    private ThemeEnum theme;
    @NotBlank(groups= {IValidatorTheme.class})
    private String description;

    public ThemeDTO(ThemeEntity theme){
        this.idTheme = theme.getIdTheme();
        this.theme = theme.getTheme();
        this.description = theme.getDescription();
    }
}