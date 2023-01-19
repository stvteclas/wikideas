package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
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
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping(value = "/article")
public class ArticleController {
    private final ArticleService articleService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody ArticleDTO article) {
        return articleService.createArticle(article);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping ("/update/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO article){
        return articleService.updateArticle(id, article);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ArticleResponseDTO> getArticleByTitle(@RequestBody ArticleDTO article){
        return articleService.getArticleByTitle(article);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/articles")
    public List<ArticleDTO> showAllArticles (){
        return articleService.showAllArticles();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/theme/{theme}")
    public List<ArticleDTO> showArticlesByTheme(@PathVariable String theme){
        return articleService.showArticlesByTheme(theme);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/articlepage")
    public ResponseEntity<Page<ArticleEntity>> showAccountsPage(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int size, Model model){
        PageRequest pageRequest = PageRequest.of(pageNumber, size);

        articleService.addNavigationAttributesToModel(pageNumber,model,pageRequest);

        return articleService.showAccountsPage(pageRequest);
    }




}
