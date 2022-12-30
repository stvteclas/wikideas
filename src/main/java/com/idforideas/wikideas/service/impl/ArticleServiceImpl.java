package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleRequestDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.exception.GlobalExceptionHandler;
import com.idforideas.wikideas.exception.MessageErrorEnum;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.repository.ArticleDAO;
import com.idforideas.wikideas.repository.ArticleRepository;
import com.idforideas.wikideas.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final ArticleRepository articleRepository;
    @Override
    public ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article) {
        Optional<ArticleEntity> articleExists = Optional.ofNullable(articleRepository.findArticleByTitle(article.getTitle()));
        if (articleExists.isPresent()) {
            throw new WikiException(MessageErrorEnum.ARTICLE_EXISTS.getMessage());
        }
        ArticleEntity articleEntity = articleDAO.createArticle(article);
        ArticleResponseDTO response = ArticleResponseDTO.builder()
                .title(articleEntity.getTitle())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> updateArticle(Long id, ArticleDTO article) {
        Optional<ArticleEntity> opArticle = articleRepository.findById(id);
        if (!opArticle.isPresent()){
            throw new WikiException("article does not  exist");
        }
        ArticleEntity articleEntity = articleDAO.updateArticle(id, article);
        ArticleDTO updateArticle = ArticleDTO.builder()
                .title(articleEntity.getTitle())
                .text(articleEntity.getText())
                .build();
        return new ResponseEntity<>("updated article", HttpStatus.OK);
    }

    @Override
    public ArticleEntity getArticleByTitle(ArticleEntity article)  {
        Optional<ArticleEntity> opArticle = Optional.ofNullable(articleRepository.findArticleByTitle(article.getTitle()));

        if (!opArticle.isPresent()){
           throw new WikiException("title does not  exist");
        }
        ArticleEntity article1 = articleDAO.findByTitle(opArticle.get().getTitle());

        return article1;

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
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
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

}
