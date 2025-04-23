package direto.grao.articleapi.controller;

import direto.grao.articleapi.dto.request.ArticleRequestDto;
import direto.grao.articleapi.dto.response.ArticleListResponseDto;
import direto.grao.articleapi.dto.response.ArticleResponseDto;
import direto.grao.articleapi.model.Article;
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
    public ResponseEntity<List<ArticleListResponseDto>> getAllArticles() {
        return  ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto) {
        Article createdArticle = articleService.save(articleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Integer id,
            @Valid @RequestBody ArticleRequestDto articleRequestDto) {

        ArticleResponseDto updated = articleService.update(id, articleRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }


}
