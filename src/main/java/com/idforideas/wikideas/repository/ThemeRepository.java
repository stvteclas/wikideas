package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.model.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
}
