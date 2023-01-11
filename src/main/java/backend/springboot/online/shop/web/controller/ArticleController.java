package backend.springboot.online.shop.web.controller;

import backend.springboot.online.shop.model.Article;
import backend.springboot.online.shop.service.ArticleService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleServices;

    public ArticleController(ArticleService articleServices) {
        this.articleServices = articleServices;
    }

    @GetMapping
    public ResponseEntity<List<Article>> findAll() {
        try {
            return new ResponseEntity<> (articleServices.findAllArticles (), HttpStatus.OK);
        } catch (Throwable throwable) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage ());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Article>> find(@PathVariable("id") @NotNull Long id) {
        try {
            return new ResponseEntity<> (articleServices.findArticle (id), HttpStatus.OK);
        } catch (NoSuchElementException elementException) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, elementException.getMessage ());
        } catch (ResponseStatusException exception) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage ());
        }
    }

    @PostMapping
    public void Article(@RequestBody Article article) {

           articleServices.saveArticle (article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull Long id) {
        try {
            articleServices.deleteArticle (id);
            return new ResponseEntity<> (HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, invalidData.getMessage ());
        } catch (Throwable throwable) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage ());
        }
    }
}

