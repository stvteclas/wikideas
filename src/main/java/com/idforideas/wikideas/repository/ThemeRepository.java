package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.model.ThemeEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
    ThemeEntity findByName(ThemeEnum theme);
}
