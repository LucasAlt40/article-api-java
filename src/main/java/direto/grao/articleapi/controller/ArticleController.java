package direto.grao.articleapi.controller;

import direto.grao.articleapi.dto.ArticleDto;
import direto.grao.articleapi.dto.CommentDto;
import direto.grao.articleapi.model.Article;
import direto.grao.articleapi.model.Comment;
import direto.grao.articleapi.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return  ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto) {
        Article createdArticle = articleService.save(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticleById(id));
    }




}
