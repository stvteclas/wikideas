package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleDTO {

    private Long id;
    private String title;
    private String text;
    private String image;
    @Enumerated(value = EnumType.STRING)
    private ThemeEnum theme;

    public ArticleDTO(ArticleEntity article) {
        id = article.getIdArticle();
        title = article.getTitle();
        text = article.getText();
        theme = article.getTheme().getTheme();
        image = article.getImage();
    }
}
