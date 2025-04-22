package direto.grao.articleapi.dto.response;


import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponseDto(
        String title,
        String author,
        String content,
        String image,
        LocalDateTime createdAt,
        List<CategoryResponseDto> categories
) {
}
