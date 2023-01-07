package com.idforideas.wikideas.service.impl;

import com.idforideas.wikideas.dto.ArticleDTO;
import com.idforideas.wikideas.dto.ArticleResponseDTO;
import com.idforideas.wikideas.dto.ThemeDTO;
import com.idforideas.wikideas.dto.validator.IValidatorArticle;
import com.idforideas.wikideas.exception.MessageErrorEnum;
import com.idforideas.wikideas.exception.WikiException;
import com.idforideas.wikideas.model.ArticleEntity;
import com.idforideas.wikideas.model.ThemeEnum;
import com.idforideas.wikideas.repository.ArticleDAO;
import com.idforideas.wikideas.repository.ArticleRepository;
import com.idforideas.wikideas.repository.ThemeRepository;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;

    @Override
    public ResponseEntity<ArticleResponseDTO> createArticle(ArticleDTO article) {
        DTOValidator.validate(article, IValidatorArticle.class);
        String articleTest = article.getTitle().toUpperCase();
        Optional<ArticleEntity> articleExists = articleDAO.getByTitle(articleTest);
        if (articleExists.isPresent()) {
            throw new WikiException(MessageErrorEnum.ARTICLE_EXISTS.getMessage());
        }
        Optional<ThemeEnum> theme1 = Optional.ofNullable(article.getTheme());
        if (!theme1.isPresent()){
            throw new WikiException(MessageErrorEnum.INVALID_THEME.getMessage());
        }
        ThemeDTO themeDTO = ThemeDTO.builder()
                .theme(theme1.get())
                .description(theme1.get().getTheme())
                .build();

        ArticleEntity articleEntity = articleDAO.createArticle(article, themeDTO);
        ArticleResponseDTO response = ArticleResponseDTO.builder()
                .text(articleEntity.getText())
                .id(articleEntity.getIdArticle())
                .image(articleEntity.getImage())
                .creationDate(articleEntity.getCreationDate())
                .title(articleEntity.getTitle())
                .theme(articleEntity.getTheme().getTheme())
                .updateDate(articleEntity.getUpdateDate())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> updateArticle(Long id, ArticleDTO article) {
        DTOValidator.validate(article, IValidatorArticle.class);
        Optional<ArticleEntity> opArticle = articleDAO.findById(id);
        if (!opArticle.isPresent()){
            throw new WikiException("article does not  exist");
        }
        ThemeDTO themeDTO = ThemeDTO.builder()
                .theme(article.getTheme())
                .description(String.valueOf(article.getTheme()))
                .build();
        ArticleEntity articleEntity = articleDAO.updateArticle(id, article, themeDTO);
        ArticleResponseDTO response = ArticleResponseDTO.builder()
                .text(articleEntity.getText())
                .id(articleEntity.getIdArticle())
                .image(articleEntity.getImage())
                .creationDate(articleEntity.getCreationDate())
                .title(articleEntity.getTitle())
                .theme(articleEntity.getTheme().getTheme())
                .updateDate(articleEntity.getUpdateDate())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ArticleResponseDTO getArticleByTitle(ArticleDTO article)  {
        String articleTest = article.getTitle().toUpperCase();
        Optional<ArticleEntity> opArticle = articleDAO.getByTitle(articleTest);
        if (!opArticle.isPresent()){
           throw new WikiException("title does not  exist");
        }
    return ArticleResponseDTO.builder()
            .id(opArticle.get().getIdArticle())
            .title(opArticle.get().getTitle())
            .text(opArticle.get().getText())
            .image(opArticle.get().getImage())
            .creationDate(opArticle.get().getCreationDate())
            .updateDate(opArticle.get().getUpdateDate())
            .theme(opArticle.get().getTheme().getTheme())
            .build();

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

    @Override
    public ArticleResponseDTO getArticleById(Long id) {
        Optional<ArticleEntity> opArticle = articleDAO.findById(id);
        if (!opArticle.isPresent()){
            throw new WikiException("article does not  exist");
        }
        ArticleResponseDTO articleResponseDTO = ArticleResponseDTO.builder()
                .id(opArticle.get().getIdArticle())
                .title(opArticle.get().getTitle())
                .text(opArticle.get().getText())
                .image(opArticle.get().getImage())
                .creationDate(opArticle.get().getCreationDate())
                .updateDate(opArticle.get().getUpdateDate())
                .theme(opArticle.get().getTheme().getTheme())
                .build();
        return articleResponseDTO;
    }

    @Override
    public List<ArticleDTO> showArticlesByTheme(ThemeDTO theme) {
        return articleDAO.showArticlesByTheme(theme);
    }

}
