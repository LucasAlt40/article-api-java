package direto.grao.articleapi.mapper;

import direto.grao.articleapi.dto.ArticleDto;
import direto.grao.articleapi.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "categories", ignore = true)
    Article toEntity(ArticleDto dto);

}
