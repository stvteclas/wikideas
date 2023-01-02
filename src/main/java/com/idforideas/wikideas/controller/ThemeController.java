package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping("/themes")
    public List<ThemeDTO> showAllArticles (){
        return themeService.showAllArticles();
    }

}
