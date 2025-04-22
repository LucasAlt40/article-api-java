package direto.grao.articleapi.service;

import direto.grao.articleapi.dto.response.CategoryResponseDto;
import direto.grao.articleapi.mapper.CategoryMapper;
import direto.grao.articleapi.model.Category;
import direto.grao.articleapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryResponseDto> getAllCategories() {
        return mapper.toCategoryListDto(categoryRepository.findAll());
    }
}
