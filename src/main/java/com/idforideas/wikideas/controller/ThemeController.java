package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/themes")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping()
    public List<ThemeDTO> showAllThemes (){
        return themeService.showAllThemes();
    }

    @GetMapping("/{id}")
    public ThemeDTO getArticleById(@PathVariable Long id){
        return themeService.getArticleById(id);
    }

}
