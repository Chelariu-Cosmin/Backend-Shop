package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
