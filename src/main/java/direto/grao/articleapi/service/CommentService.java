package direto.grao.articleapi.service;

import direto.grao.articleapi.dto.CommentDto;
import direto.grao.articleapi.exceptions.BusinessException;
import direto.grao.articleapi.exceptions.ResourceNotFoundException;
import direto.grao.articleapi.mapper.ArticleMapper;
import direto.grao.articleapi.mapper.CommentMapper;
import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Comment;
import direto.grao.articleapi.repository.ArticleRepository;
import direto.grao.articleapi.repository.CategoryRepository;
import direto.grao.articleapi.repository.CommentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    public CommentService(ArticleRepository articleRepository, CategoryRepository categoryRepository, CommentRepository commentRepository, CommentMapper mapper) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    public Comment addComment(CommentDto commentDto) {
        Comment comment = mapper.toEntity(commentDto);

        Optional<Article> article = articleRepository.findById(commentDto.articleId());

        if(article.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum Artigo foi encontrado com esse id");
        }

        comment.setArticle(article.get());

        try {
            return commentRepository.save(comment);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao salvar comentário: dados inválidos", e);
        }
    }
}
