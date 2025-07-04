package direto.grao.articleapi.dto.response;


import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponseDto(
        Integer id,
        String title,
        String author,
        String content,
        String resume,
        String image,
        LocalDateTime createdAt,
        List<CategoryResponseDto> categories
) {
}
