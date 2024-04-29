package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.CategoryMapper;
import com.example.webpos.model.Category;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.CategoriesApi;
import org.springframework.samples.petclinic.rest.dto.CategoryDto;
import org.springframework.samples.petclinic.rest.dto.CategoryDto;
import org.springframework.samples.petclinic.rest.dto.CategoryFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CategoryController implements CategoriesApi {
    private final PosService posService;
    private final CategoryMapper categoryMapper;

    public CategoryController(PosService posService, CategoryMapper categoryMapper) {
        this.posService = posService;
        this.categoryMapper = categoryMapper;
    }
    
    @Override
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categorys = new ArrayList<>(categoryMapper.toCategoryDtos(this.posService.findAllCategories()));
        if (categorys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> showCategoryById(Long categoryId) {
        Category category = this.posService.findCategoryById(categoryId);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryMapper.toCategoryDto(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> addCategory(CategoryFieldsDto categoryFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Category category = categoryMapper.toCategory(categoryFieldsDto);
        this.posService.saveCategory(category);
        CategoryDto categoryDto = categoryMapper.toCategoryDto(category);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/categorys/{id}")
                .buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<>(categoryDto, headers, HttpStatus.CREATED);
    }
}
