package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ArticleResponseDTO> getArticleByTitle(@RequestBody ArticleDTO article){
        return articleService.getArticleByTitle(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @GetMapping("/articles")
    public List<ArticleDTO> showAllArticles (){
        return articleService.showAllArticles();
    }

    @GetMapping("/theme")
    public List<ArticleDTO> showArticlesByTheme(@RequestBody ThemeDTO theme){
        return articleService.showArticlesByTheme(theme);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }

    @GetMapping("/articlepage")
    public ResponseEntity<Page<ArticleEntity>> showAccountsPage(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int size, Model model){
        PageRequest pageRequest = PageRequest.of(pageNumber, size);

        articleService.addNavigationAttributesToModel(pageNumber,model,pageRequest);

        return articleService.showAccountsPage(pageRequest);
    }




}
