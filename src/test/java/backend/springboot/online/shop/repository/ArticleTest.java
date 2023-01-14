package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ArticleTest {
    @Mock
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @WithMockUser(username = "testuser", password = "1234", roles = "USER", authorities = {"ROLE_ADMIN"})
    public void testCreateNewArticle() {
        Article newArticle;
        newArticle = new Article ("bluza albastra", "Jichael Mackson", new BigDecimal (199), "photo_url", null);

        Article savedArticle = articleRepository.save (newArticle);
        assertThat (savedArticle.getId ()).isGreaterThan (0);
    }

    @Test
    public void testFindAllArticle() {
        Article findArticle = getArticle ();
        articleRepository.save (findArticle);
        List<Article> result = new ArrayList<Article> (articleRepository.findAll ());
        assertEquals (result.size (), 10);
    }

    private Article getArticle() {
        Article findArticle = new Article ();
        findArticle.setId (Long.valueOf (1));
        findArticle.setDescription ("Jickael Mackson");
        findArticle.setName ("bluza_hihi");
        findArticle.setPrice (BigDecimal.valueOf (450000));
        findArticle.setPhoto ("photo_url");
        findArticle.setOrder (null);
        return findArticle;
    }
}
