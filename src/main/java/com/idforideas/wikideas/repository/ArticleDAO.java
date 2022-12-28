package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public ArticleEntity updateArticle(Long id , ArticleDTO article){
        Optional<ArticleEntity> articleUpdate = articleRepository.findById(id);
        articleUpdate.get().setText(article.getText());
        articleUpdate.get().setTitle(article.getTitle());
        return articleRepository.saveAndFlush(articleUpdate.get());
    }

    public ArticleEntity findByTitle(String title){
        return articleRepository.findArticleByTitle(title);
    }

    public Optional<ArticleEntity> getByTitle(String title){
        return Optional.ofNullable(articleRepository.findArticleByTitle(title));
    }

}
