package com.idforideas.wikideas.repository;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.model.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findArticleByTitle(String title);



}
