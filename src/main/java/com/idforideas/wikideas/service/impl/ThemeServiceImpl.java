package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.repository.ThemeRepository;
import com.idforideas.wikideas.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {


    private final ThemeRepository themeRepository;

    @Override
    public List<ThemeDTO> showAllArticles() {
        List<ThemeEntity> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeDTO::new)
                .collect(Collectors.toList());
    }
}
