package direto.grao.articleapi.repository;

import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArticle(Article article);
}
