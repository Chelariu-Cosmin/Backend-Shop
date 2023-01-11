package backend.springboot.online.shop.service;

import backend.springboot.online.shop.model.Article;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAllArticles();

    Optional<Article> findArticle(Long id);

    void saveArticle(Article article);

    void deleteArticle(Long id);

    Page<Article> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
