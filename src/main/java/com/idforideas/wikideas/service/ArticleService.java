package com.idforideas.wikideas.service;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleRequestDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {

    ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article);

    ResponseEntity<Object> updateArticle(Long id, ArticleDTO article);


    ArticleEntity getArticleByTitle(ArticleEntity article);

    List<ArticleEntity> showAllArticles();

    ResponseEntity<Object> deleteArticle(Long id);
}
