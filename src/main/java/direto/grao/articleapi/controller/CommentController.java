package direto.grao.articleapi.controller;

import direto.grao.articleapi.dto.CommentDto;
import direto.grao.articleapi.model.Comment;
import direto.grao.articleapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentDto commentDto) {
        Comment createdComment = commentService.addComment(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/getAllByArticleId/{articleId}")
    public ResponseEntity<List<Comment>> getAllByArticleId(@PathVariable Integer articleId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(commentService.getAllCommentsByArticle(articleId));
    }

    @PostMapping("/{parentCommentId}/reply")
    public ResponseEntity<Comment> replyToComment(
            @PathVariable Integer parentCommentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        Comment reply = commentService.replyToComment(parentCommentId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }
}
