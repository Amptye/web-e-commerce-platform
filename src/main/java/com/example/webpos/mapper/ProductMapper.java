package com.example.webpos.mapper;

import com.example.webpos.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import org.springframework.samples.petclinic.rest.dto.ProductFieldsDto;
import org.springframework.samples.petclinic.rest.dto.UserFieldsDto;

import java.util.Collection;
import java.util.*;

@Mapper
public interface ProductMapper {

    @Mapping(source = "owner.uid", target = "ownerId")
    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);

    Product toProduct(ProductFieldsDto productFieldsDto);

    List<ProductDto> toProductDtos(Collection<Product> productCollection);

    Collection<Product> toProducts(Collection<ProductDto> productDtos);
}
