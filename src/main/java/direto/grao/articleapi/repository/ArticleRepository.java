package direto.grao.articleapi.repository;

import direto.grao.articleapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
