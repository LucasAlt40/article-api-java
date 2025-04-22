package direto.grao.articleapi.controller;

import direto.grao.articleapi.dto.request.CommentRequestDto;
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
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        Comment createdComment = commentService.addComment(commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/getAllByArticleId/{articleId}")
    public ResponseEntity<List<Comment>> getAllByArticleId(@PathVariable Integer articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsByArticle(articleId));
    }

    @PostMapping("/{parentCommentId}/reply")
    public ResponseEntity<Comment> replyToComment(
            @PathVariable Integer parentCommentId,
            @Valid @RequestBody CommentRequestDto commentRequestDto
    ) {
        Comment reply = commentService.replyToComment(parentCommentId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }
}
