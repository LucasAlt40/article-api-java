package direto.grao.articleapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDto(
        @NotBlank(message = "Autor não pode ficar em branco") String author,
        @NotBlank(message = "Conteúdo não pode ficar em branco") String content,
        @NotNull(message = "É preciso informar o ID do artigo para adicionar um comentário") Integer articleId
) {
}
