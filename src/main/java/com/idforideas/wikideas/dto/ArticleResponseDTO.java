package com.idforideas.wikideas.dto;

import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ArticleResponseDTO {

    private Long id;
    private String title;
    private String text;

    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @Enumerated(value = EnumType.STRING)
    private ThemeEnum theme;


    public ArticleResponseDTO(ArticleEntity article) {
        id = article.getIdArticle();
        title = article.getTitle();
        text = article.getText();
        theme = article.getTheme().getTheme();
        image = article.getImage();
        creationDate = article.getCreationDate();
        updateDate = article.getUpdateDate();
    }
}
