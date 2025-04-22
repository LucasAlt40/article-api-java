package direto.grao.articleapi.dto.response;

import direto.grao.articleapi.model.Category;

import java.util.List;

public record ArticleListResponseDto(
        String title,
        String resume,
        String author,
        String image,
        List<Category> categories
) {
}
