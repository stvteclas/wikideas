package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ArticleDTO;
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
}
