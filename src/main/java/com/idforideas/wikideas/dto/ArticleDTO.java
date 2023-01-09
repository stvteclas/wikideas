package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.dto.validator.IValidatorArticle;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleDTO {

    private Long id;
    @NotBlank(groups= {IValidatorArticle.class})
    private String title;
    @NotBlank(groups= {IValidatorArticle.class})
    private String text;
    @NotBlank(groups= {IValidatorArticle.class})
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
