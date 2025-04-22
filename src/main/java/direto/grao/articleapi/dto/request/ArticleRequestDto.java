package direto.grao.articleapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record ArticleRequestDto(
        @NotBlank(message = "Titulo não deve estar em branco") String title,
        @NotBlank(message = "Conteúdo não deve estar em branco") String content,
        @NotBlank(message = "Resumo não deve estar em branco") String resume,
        @NotBlank(message = "Autor não deve estar em branco") String author,
        @NotEmpty(message = "É preciso selecionar ao menos uma categoria") Set<Integer> categoriesId
) {}

