package com.idforideas.wikideas.service;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleRequestDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

public interface ArticleService {

    ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article);

    ResponseEntity<Object> updateArticle(Long id, ArticleDTO article);


    ArticleEntity getArticleByTitle(ArticleEntity article);

    List<ArticleDTO> showAllArticles();

    ResponseEntity<Object> deleteArticle(Long id);

    void addNavigationAttributesToModel(int pageNumber, Model model, PageRequest pageRequest);

    ResponseEntity<Page<ArticleEntity>> showAccountsPage(PageRequest pageRequest);

    ArticleDTO getArticleById(Long id);
}
