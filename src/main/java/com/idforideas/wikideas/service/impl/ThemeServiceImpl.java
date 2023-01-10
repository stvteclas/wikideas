package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.repository.ThemeRepository;
import com.idforideas.wikideas.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {


    private final ThemeRepository themeRepository;


    @Override
    public List<ThemeDTO> showAllThemes() {
        List<ThemeEntity> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeDTO::new)
                .toList();
    }

    @Override
    public List<ThemeDTO> getThemeById(Long id) {
        Optional<ThemeEntity> themeEntity = themeRepository.findById(id);
        if (!themeEntity.isPresent()){
            throw new WikiException("id theme does not  exist");
        }
        return themeEntity.stream()
                .map(ThemeDTO::new)
                .toList();
    }
}
