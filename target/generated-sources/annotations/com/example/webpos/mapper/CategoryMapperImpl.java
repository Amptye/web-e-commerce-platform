package com.example.webpos.mapper;

import com.example.webpos.model.Category;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.samples.petclinic.rest.dto.CategoryDto;
import org.springframework.samples.petclinic.rest.dto.CategoryFieldsDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName( category.getName() );
        categoryDto.setId( category.getId() );

        return categoryDto;
    }

    @Override
    public Category toCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        if ( categoryDto.getId() != null ) {
            category.setId( categoryDto.getId() );
        }
        category.setName( categoryDto.getName() );

        return category;
    }

    @Override
    public Category toCategory(CategoryFieldsDto categoryFieldsDto) {
        if ( categoryFieldsDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryFieldsDto.getName() );

        return category;
    }

    @Override
    public List<CategoryDto> toCategoryDtos(Collection<Category> categoryCollection) {
        if ( categoryCollection == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categoryCollection.size() );
        for ( Category category : categoryCollection ) {
            list.add( toCategoryDto( category ) );
        }

        return list;
    }

    @Override
    public Collection<Category> toCategorys(Collection<CategoryDto> categoryDtos) {
        if ( categoryDtos == null ) {
            return null;
        }

        Collection<Category> collection = new ArrayList<Category>( categoryDtos.size() );
        for ( CategoryDto categoryDto : categoryDtos ) {
            collection.add( toCategory( categoryDto ) );
        }

        return collection;
    }
}
