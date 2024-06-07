package com.example.webpos.mapper;

import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import org.springframework.samples.petclinic.rest.dto.ProductFieldsDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setOwnerId( productOwnerUid( product ) );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setImage( product.getImage() );
        productDto.setQuantity( product.getQuantity() );
        productDto.setId( product.getId() );

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        product.setName( productDto.getName() );
        if ( productDto.getPrice() != null ) {
            product.setPrice( productDto.getPrice() );
        }
        product.setImage( productDto.getImage() );
        if ( productDto.getQuantity() != null ) {
            product.setQuantity( productDto.getQuantity() );
        }

        return product;
    }

    @Override
    public Product toProduct(ProductFieldsDto productFieldsDto) {
        if ( productFieldsDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productFieldsDto.getName() );
        if ( productFieldsDto.getPrice() != null ) {
            product.setPrice( productFieldsDto.getPrice() );
        }
        product.setImage( productFieldsDto.getImage() );
        if ( productFieldsDto.getQuantity() != null ) {
            product.setQuantity( productFieldsDto.getQuantity() );
        }

        return product;
    }

    @Override
    public List<ProductDto> toProductDtos(Collection<Product> productCollection) {
        if ( productCollection == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( productCollection.size() );
        for ( Product product : productCollection ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }

    @Override
    public Collection<Product> toProducts(Collection<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        Collection<Product> collection = new ArrayList<Product>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            collection.add( toProduct( productDto ) );
        }

        return collection;
    }

    private Long productOwnerUid(Product product) {
        if ( product == null ) {
            return null;
        }
        User owner = product.getOwner();
        if ( owner == null ) {
            return null;
        }
        long uid = owner.getUid();
        return uid;
    }
}
