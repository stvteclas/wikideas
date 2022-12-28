package com.idforideas.wikideas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleDTO {
    @NotNull
    private String title;

    @NotNull
    private String text;

//    @NotNull
//    private String idTheme;
}
