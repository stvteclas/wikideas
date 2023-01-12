package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.exception.MessageErrorEnum;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;

    public Optional<ArticleEntity> findById(Long id ){
        return articleRepository.findById(id);
    }
    public void findByTitle(ArticleDTO article ){
        List<String> articles = articleRepository.findAll()
                .stream()
                .map(ArticleEntity::getTitle)
                .toList();
        Optional<String> articleExist = articles.stream()
                .filter(element -> element.equalsIgnoreCase(article.getTitle()))
                .findFirst();
        if (articleExist.isPresent()) {
            throw new WikiException(MessageErrorEnum.ARTICLE_EXISTS.getMessage());
        }
    }

    public ArticleEntity createArticle(ArticleDTO article){
        Optional<ThemeEnum> theme1 = Optional.ofNullable(article.getTheme());
        if (!theme1.isPresent()){
            throw new WikiException(MessageErrorEnum.INVALID_THEME.getMessage());
        }
        ThemeEntity themeEntity = themeRepository.findByTheme(article.getTheme());
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(article.getTitle())
                .text(article.getText())
                .theme(themeEntity)
                .image(article.getImage())
                .build();
        return articleRepository.saveAndFlush(articleEntity);
    }

    public ArticleEntity updateArticle(Long id, ArticleDTO article){
        Optional<ArticleEntity> articleUpdate = articleRepository.findById(id);
        if (!articleUpdate.isPresent()){
            throw new WikiException("Article does not exist");
        }
       ThemeEntity themeEntity = themeRepository.findByTheme(article.getTheme());
        articleUpdate.get().setText(article.getText());
        articleUpdate.get().setTitle(article.getTitle());
        articleUpdate.get().setImage(article.getImage());
        articleUpdate.get().setTheme(themeEntity);
        return articleRepository.saveAndFlush(articleUpdate.get());
    }

    public ArticleEntity getByTitle(ArticleDTO article){
        List<String> articles = articleRepository.findAll()
                .stream()
                .map(ArticleEntity::getTitle)
                .toList();
        Optional<String> articleExist = articles.stream()
                .filter(element -> element.equalsIgnoreCase(article.getTitle()))
                .findFirst();
        if (!articleExist.isPresent()){
            throw new WikiException(MessageErrorEnum.INVALID_TITLE.getMessage());
        }
        ArticleEntity articleEntity = articleRepository.findArticleByTitle(articleExist.get());
        return articleEntity;
    }

    public List<ArticleDTO> getAll() {
        List<ArticleEntity> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleDTO::new)
                .toList();
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Page<ArticleEntity> showAccountsPage(PageRequest pageRequest) {
        return articleRepository.findAll(pageRequest);
    }

    public List<ArticleDTO> showArticlesByTheme(String theme) {
        ThemeEnum themeEnum = ThemeEnum.valueOf(theme);
        Optional<ThemeEntity> themeEntity = Optional.ofNullable(themeRepository.findByTheme(themeEnum));

       if (!themeEntity.isPresent()){
            throw new WikiException(MessageErrorEnum.INVALID_THEME.getMessage());
        }

        List<ArticleEntity> articles = articleRepository.findByTheme(themeEntity.get());

        return articles.stream()
                .map(ArticleDTO::new)
                .toList();
    }
}
