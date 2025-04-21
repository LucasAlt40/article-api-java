package direto.grao.articleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record ArticleDto(
        @NotBlank(message = "Titulo não deve estar em branco") String title,
        @NotBlank(message = "Conteúdo não deve estar em branco") String content,
        @NotBlank(message = "Autor não deve estar em branco") String author,
        @NotEmpty(message = "É preciso selecionar ao menos uma categoria") Set<Integer> categoriesId
) {}

