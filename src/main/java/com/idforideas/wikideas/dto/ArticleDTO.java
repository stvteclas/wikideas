package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.model.ArticleEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleDTO {

    private Long id;
    @NotNull
    private String title;

    @NotNull
    private String text;


    public ArticleDTO(ArticleEntity article) {
        id = article.getIdArticle();
        title = article.getTitle();
        text = article.getText();
    }


//    @NotNull
//    private String idTheme;
}
