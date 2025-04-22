package direto.grao.articleapi.mapper;

import direto.grao.articleapi.dto.request.ArticleRequestDto;
import direto.grao.articleapi.dto.response.ArticleListResponseDto;
import direto.grao.articleapi.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "categories", ignore = true)
    Article toEntity(ArticleRequestDto dto);

    ArticleListResponseDto toListDto(Article article);
}
