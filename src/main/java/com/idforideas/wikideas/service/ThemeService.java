package com.idforideas.wikideas.service;

import com.idforideas.wikideas.dto.ThemeDTO;

import java.util.List;

public interface ThemeService {
    List<ThemeDTO> showAllThemes();

    List<ThemeDTO> getThemeById(Long id);
}
