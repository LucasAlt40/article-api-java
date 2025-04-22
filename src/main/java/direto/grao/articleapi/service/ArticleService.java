package direto.grao.articleapi.service;

import direto.grao.articleapi.dto.request.ArticleRequestDto;
import direto.grao.articleapi.dto.response.ArticleListResponseDto;
import direto.grao.articleapi.exceptions.BusinessException;
import direto.grao.articleapi.exceptions.ResourceNotFoundException;
import direto.grao.articleapi.mapper.ArticleMapper;
import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Category;
import direto.grao.articleapi.repository.ArticleRepository;
import direto.grao.articleapi.repository.CategoryRepository;
import direto.grao.articleapi.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


    public Article save(ArticleRequestDto articleRequestDto) {
        Article article = mapper.toEntity(articleRequestDto);

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(articleRequestDto.categoriesId()));

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma categoria válida encontrada");
        }

        if (categories.size() != articleRequestDto.categoriesId().size()) {
            throw new ResourceNotFoundException("Algumas categorias não foram encontradas");
        }

        article.setCategories(categories);

        try {
            return articleRepository.save(article);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao salvar artigo: dados inválidos", e);
        }
    }

    public Article getArticleById(Integer id) {
        Optional<Article> article = articleRepository.getArticleById(id);

        if(article.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum Artigo foi encontrado com esse id: " + id);
        }

        return article.get();
    }

    @Transactional
    public List<ArticleListResponseDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
    }


}
