package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
    ThemeEntity findByTheme(ThemeEnum theme);
}
