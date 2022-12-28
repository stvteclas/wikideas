package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;

    public ArticleEntity createArticle(ArticleDTO article){
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(article.getTitle())
                .text(article.getText())
                .build();
        return articleRepository.saveAndFlush(articleEntity);
    }
}
