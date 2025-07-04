package direto.grao.articleapi.mapper;

import direto.grao.articleapi.dto.request.CommentRequestDto;
import direto.grao.articleapi.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "article", ignore = true)
    Comment toEntity(CommentRequestDto dto);
}
