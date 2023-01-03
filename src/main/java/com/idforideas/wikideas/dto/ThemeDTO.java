package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ThemeDTO {

    private Long idTheme;
    private ThemeEnum theme;

    private String description;

    public ThemeDTO(ThemeEntity theme){
        this.idTheme = theme.getIdTheme();
        this.theme = theme.getTheme();
        this.description = theme.getDescription();
    }
}