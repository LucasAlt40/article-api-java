package direto.grao.articleapi.mapper;

import direto.grao.articleapi.dto.response.CategoryResponseDto;
import direto.grao.articleapi.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponseDto> toCategoryListDto(List<Category> category);
}
