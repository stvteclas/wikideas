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


    private ThemeEnum name;

    private String description;

    public ThemeDTO(ThemeEntity theme){
        name = theme.getName();
        description = theme.getDescription();
    }
}