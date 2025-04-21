package direto.grao.articleapi.controller;

import direto.grao.articleapi.dto.CommentDto;
import direto.grao.articleapi.model.Comment;
import direto.grao.articleapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentDto commentDto) {
        Comment createdComment = commentService.addComment(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
