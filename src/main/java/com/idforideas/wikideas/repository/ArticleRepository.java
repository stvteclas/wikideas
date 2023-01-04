package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findArticleByTitle(String title);

    List<ArticleEntity> findByTheme(ThemeEntity theme);
}
