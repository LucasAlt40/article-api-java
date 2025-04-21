package direto.grao.articleapi.repository;

import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findArticlesByTitle(String title);
    List<Article> findArticlesByCategories(Set<Category> categories);
}
