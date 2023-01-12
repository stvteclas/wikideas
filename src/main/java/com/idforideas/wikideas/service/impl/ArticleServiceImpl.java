package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.dto.validator.IValidatorArticle;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.repository.ArticleDAO;
import com.idforideas.wikideas.service.ArticleService;
import com.idforideas.wikideas.utils.DTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    @Override
    public ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article) {
        DTOValidator.validate(article, IValidatorArticle.class);
        articleDAO.findByTitle(article);
        ArticleEntity articleEntity = articleDAO.createArticle(article);
        ArticleResponseDTO response = responseDTO(articleEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> updateArticle(Long id, ArticleDTO article) {
        DTOValidator.validate(article, IValidatorArticle.class);
        ArticleEntity articleEntity = articleDAO.updateArticle(id, article);
        ArticleResponseDTO response = responseDTO(articleEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticleResponseDTO> getArticleByTitle(ArticleDTO article)  {
        ArticleEntity articleEntity = articleDAO.getByTitle(article);
        ArticleResponseDTO response = responseDTO(articleEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<ArticleDTO> showAllArticles() {
        return articleDAO.getAll();
    }

    @Override
    public ResponseEntity<Object> deleteArticle(Long id) {
        articleDAO.deleteArticleById(id);
        return new ResponseEntity<>("Deleted Article", HttpStatus.OK);
    }

    @Override
    public void addNavigationAttributesToModel(int pageNumber, Model model, PageRequest pageRequest) {
        Page<ArticleEntity> pageAccounts = articleDAO.showAccountsPage(pageRequest);

        int totalPages = pageAccounts.getTotalPages();
        if(totalPages > 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pages", pages);
            model.addAttribute("current", pageNumber+1);
            model.addAttribute("next", pageNumber+2);
            model.addAttribute("prev", pageNumber);
            model.addAttribute("last", totalPages);
        }

        model.addAttribute("List", pageAccounts.getContent());
    }

    @Override
    public ResponseEntity<Page<ArticleEntity>> showAccountsPage(PageRequest pageRequest) {
        Page<ArticleEntity> pageAccounts = articleDAO.showAccountsPage(pageRequest);

        if (pageAccounts.isEmpty()){
            throw new WikiException("Missing page number");
        }

        return ResponseEntity.ok(pageAccounts);
    }

    @Override
    public ResponseEntity<ArticleResponseDTO> getArticleById(Long id) {
        Optional<ArticleEntity> opArticle = articleDAO.findById(id);
        if (!opArticle.isPresent()){
            throw new WikiException("article does not  exist");
        }
        ArticleResponseDTO response = responseDTO(opArticle.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<ArticleDTO> showArticlesByTheme(String theme) {
        return articleDAO.showArticlesByTheme(theme);
    }
    private ArticleResponseDTO responseDTO(ArticleEntity articleEntity){
        return ArticleResponseDTO.builder()
                .text(articleEntity.getText())
                .id(articleEntity.getIdArticle())
                .image(articleEntity.getImage())
                .creationDate(articleEntity.getCreationDate())
                .title(articleEntity.getTitle())
                .theme(articleEntity.getTheme().getTheme())
                .updateDate(articleEntity.getUpdateDate())
                .build();
    }
}
