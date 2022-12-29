package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleRequestDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody ArticleDTO article) {
        return articleService.createArticle(article);
    }

    @PatchMapping ("/update/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO article){
        return articleService.updateArticle(id, article);
    }

    @GetMapping
    public ArticleEntity getArticleByTitle(@RequestBody ArticleEntity article){
        return articleService.getArticleByTitle(article);
    }

    @GetMapping("/articles")
    public List<ArticleResponseDTO> showAllArticles (){
        return articleService.showAllArticles();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }




}
