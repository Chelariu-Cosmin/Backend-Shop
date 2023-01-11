package backend.springboot.online.shop.service.impl;

import backend.springboot.online.shop.model.Article;
import backend.springboot.online.shop.repository.ArticleRepository;
import backend.springboot.online.shop.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAllArticles() {
        List<Article> articleList = new ArrayList<> ();

        articleRepository.findAll ()
                         .forEach (articleList::add);
        return articleList;
    }

    @Override
    public Optional<Article> findArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById (id);
        if (articleOptional.isPresent ()) {
            return articleRepository.findById (id);
        } else {
            throw new NoSuchElementException ("Nu exista articolul cu id:" + id);
        }
    }

    @Override
    public void saveArticle(Article article) {
         articleRepository.save (article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById (id);
    }

    @Override
    public Page<Article> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.articleRepository.findAll(pageable);
    }
}
