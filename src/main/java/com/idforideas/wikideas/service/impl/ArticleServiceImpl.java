package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleRequestDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.exception.MessageErrorEnum;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.repository.ArticleDAO;
import com.idforideas.wikideas.repository.ArticleRepository;
import com.idforideas.wikideas.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final ArticleRepository articleRepository;
    @Override
    public ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article) {
        Optional<ArticleEntity> articleExists = Optional.ofNullable(articleRepository.findArticleByTitle(article.getTitle()));
        if (articleExists.isPresent()) {
            throw new WikiException(MessageErrorEnum.ARTICLE_EXISTS.getMessage());
        }
        ArticleEntity articleEntity = articleDAO.createArticle(article);
        ArticleResponseDTO response = ArticleResponseDTO.builder()
                .title(articleEntity.getTitle())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> updateArticle(Long id, ArticleDTO article) {
        Optional<ArticleEntity> articleExists = Optional.ofNullable(articleRepository.findArticleByTitle(article.getTitle()));
        if (!articleExists.isPresent()) {
            throw new WikiException(MessageErrorEnum.INVALID_TITLE.getMessage());
        }
        ArticleEntity articleEntity = articleDAO.updateArticle(id, article);
        ArticleDTO updateArticle = ArticleDTO.builder()
                .title(articleEntity.getTitle())
                .text(articleEntity.getText())
                .build();
        return new ResponseEntity<>("updated article", HttpStatus.OK);
    }

    @Override
    public ArticleEntity getArticleByTitle(ArticleEntity article) {
        Optional<ArticleEntity> opArticle = articleDAO.getByTitle(article.getTitle());
       ArticleEntity article1 = articleDAO.findByTitle(opArticle.get().getTitle());
        if (opArticle.isEmpty()){
            throw new WikiException("The requested title does not exist");
        }

        return article1;

    }

}
