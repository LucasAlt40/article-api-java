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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Comment> getAllCommentsByArticle(Integer articleId) {

        Optional<Article> article = articleRepository.findById(articleId);

        if(article.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum Artigo foi encontrado com esse id");
        }

        List<Comment> comments = commentRepository.findAllByArticle(article.get());

        if(comments.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum comentário ainda");
        }

        return comments.stream()
                .filter(comment -> comment.getParent() == null)
                .collect(Collectors.toList());
    }


    public Comment replyToComment(Integer parentCommentId, CommentDto commentDto) {
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário pai não encontrado com id: " + parentCommentId));

        if (parentComment.getArticle().getId() != commentDto.articleId()) {
            throw new BusinessException("Comentário pai não pertence ao artigo informado.", new Exception());
        }

        Article article = articleRepository.findById(commentDto.articleId())
                .orElseThrow(() -> new ResourceNotFoundException("Artigo não encontrado com id: " + commentDto.articleId()));

        Comment reply = mapper.toEntity(commentDto);
        reply.setParent(parentComment);
        reply.setArticle(article);

        try {
            return commentRepository.save(reply);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao salvar resposta do comentário", e);
        }
    }


}
