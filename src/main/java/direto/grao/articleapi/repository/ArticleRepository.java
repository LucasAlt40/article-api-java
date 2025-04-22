package direto.grao.articleapi.repository;

import direto.grao.articleapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> getArticleById(int id);


}
