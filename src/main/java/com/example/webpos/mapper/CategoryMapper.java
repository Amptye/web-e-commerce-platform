package com.example.webpos.mapper;

import com.example.webpos.model.Category;
import org.mapstruct.Mapper;
import org.springframework.samples.petclinic.rest.dto.CategoryDto;
import org.springframework.samples.petclinic.rest.dto.CategoryFieldsDto;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    Category toCategory(CategoryFieldsDto categoryFieldsDto);

    List<CategoryDto> toCategoryDtos(Collection<Category> categoryCollection);

    Collection<Category> toCategorys(Collection<CategoryDto> categoryDtos);
}
