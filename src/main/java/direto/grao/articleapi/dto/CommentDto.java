package direto.grao.articleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentDto(
        @NotBlank(message = "Autor não pode ficar em branco") String author,
        @NotBlank(message = "Conteúdo não pode ficar em branco") String content,
        @NotNull(message = "É preciso informar o ID do artigo para adicionar um comentário") int articleId
) {
}
