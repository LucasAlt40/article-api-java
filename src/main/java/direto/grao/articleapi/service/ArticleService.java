package direto.grao.articleapi.service;

import direto.grao.articleapi.dto.ArticleDto;
import direto.grao.articleapi.dto.CommentDto;
import direto.grao.articleapi.exceptions.BusinessException;
import direto.grao.articleapi.exceptions.ResourceNotFoundException;
import direto.grao.articleapi.mapper.ArticleMapper;
import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Category;
import direto.grao.articleapi.model.Comment;
import direto.grao.articleapi.repository.ArticleRepository;
import direto.grao.articleapi.repository.CategoryRepository;
import direto.grao.articleapi.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final ArticleMapper mapper;

    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository, CommentRepository commentRepository, ArticleMapper mapper) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }


    @Transactional
    public Article save(ArticleDto articleDto) {
        Article article = mapper.toEntity(articleDto);

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(articleDto.categoriesId()));

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma categoria válida encontrada");
        }

        if (categories.size() != articleDto.categoriesId().size()) {
            throw new ResourceNotFoundException("Algumas categorias não foram encontradas");
        }

        article.setCategories(categories);

        try {
            return articleRepository.save(article);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao salvar artigo: dados inválidos", e);
        }
    }



}
