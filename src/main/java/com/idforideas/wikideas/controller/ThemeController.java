package com.idforideas.wikideas.controller;

import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping(value = "/themes")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping()
    public List<ThemeDTO> showAllThemes (){
        return themeService.showAllThemes();
    }

    @GetMapping("/{id}")
    public List<ThemeDTO> getThemeById(@PathVariable Long id){
        return themeService.getThemeById(id);
    }

}
