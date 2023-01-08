package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.exception.MessageErrorEnum;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;

    public Optional<ArticleEntity> findById(Long id ){
        return articleRepository.findById(id);
    }

    public ArticleEntity createArticle(ArticleDTO article, ThemeDTO theme){
        ThemeEntity themeEntity = getTheme(theme);
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(article.getTitle())
                .text(article.getText())
                .theme(themeEntity)
                .image(article.getImage())
                .build();
        return articleRepository.saveAndFlush(articleEntity);
    }

    public ArticleEntity updateArticle(Long id, ArticleDTO article, ThemeDTO theme){
        Optional<ArticleEntity> articleUpdate = articleRepository.findById(id);
       ThemeEntity themeEntity = getTheme(theme);
        articleUpdate.get().setText(article.getText());
        articleUpdate.get().setTitle(article.getTitle());
        articleUpdate.get().setImage(article.getImage());
        articleUpdate.get().setTheme(themeEntity);
        return articleRepository.saveAndFlush(articleUpdate.get());
    }

    public Optional<ArticleEntity> getByTitle(ArticleDTO article){
        List<ArticleEntity> articles = articleRepository.findAll();
        String articleTest="";

        for (int i=0; i< articles.size();i++){
            if (article.getTitle().equalsIgnoreCase(articles.get(i).getTitle())){
                articleTest = articles.get(i).getTitle();
                return Optional.ofNullable(articles.get(i));
            }
        }

        return Optional.ofNullable(articleRepository.findArticleByTitle(articleTest));
    }

    public List<ArticleDTO> getAll() {
        List<ArticleEntity> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());

    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Page<ArticleEntity> showAccountsPage(PageRequest pageRequest) {
        return articleRepository.findAll(pageRequest);
    }

    public ThemeEntity getTheme(ThemeDTO theme) {

        Optional<ThemeEntity> themeEntity = Optional.ofNullable(themeRepository.findByTheme(theme.getTheme()));

        if (!themeEntity.isPresent()) {
          ThemeEntity theme1 = ThemeEntity.builder()
                  .theme(theme.getTheme())
                  .description(theme.getDescription())
                  .build();
            themeRepository.saveAndFlush(theme1);
            return theme1;
        }
        return themeEntity.get();
    }

    public List<ArticleDTO> showArticlesByTheme(ThemeDTO theme) {
        Optional<ThemeEntity> themeEntity = Optional.ofNullable(themeRepository.findByTheme(theme.getTheme()));

       if (!themeEntity.isPresent()){
            throw new WikiException(MessageErrorEnum.INVALID_THEME.getMessage());
        }

        List<ArticleEntity> articles = articleRepository.findByTheme(themeEntity.get());
        return articles.stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }
}
