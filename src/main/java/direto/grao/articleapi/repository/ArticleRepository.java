package direto.grao.articleapi.repository;

import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article findArticlesByTitle(String title);
    Article findArticlesByCategories(Set<Category> categories);
}
