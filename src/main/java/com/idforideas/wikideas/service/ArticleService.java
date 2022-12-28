package com.idforideas.wikideas.service;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article);
}
