package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.ProductMapper;
import com.example.webpos.model.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.ProductsApi;
import org.springframework.samples.petclinic.rest.dto.PatchProductRequestDto;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import org.springframework.samples.petclinic.rest.dto.ProductFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class ProductController implements ProductsApi {
    private final PosService posService;

    private final ProductMapper productMapper;

    public ProductController(PosService posService, ProductMapper productMapper) {
        this.posService = posService;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<ProductDto> products = new ArrayList<>(productMapper.toProductDtos(this.posService.findAllProducts()));
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> showProductById(Long productId) {
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> addProduct(ProductFieldsDto productFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Product product = productMapper.toProduct(productFieldsDto);
        if(productFieldsDto.getQuantity() == null){
            product.setQuantity(1);
        } else {
            product.setQuantity(productFieldsDto.getQuantity());
        }
        if(product.getQuantity() <= 0){
            product.setQuantity(0);
        }
        this.posService.saveProduct(product);
        ProductDto productDto = productMapper.toProductDto(product);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(productDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> deleteProduct(Long productId) {
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.posService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(Long productId, ProductFieldsDto productFieldsDto) {
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(productFieldsDto.getName() != null) {
            product.setName(productFieldsDto.getName());
        }
        if(productFieldsDto.getImage() != null) {
            product.setImage(productFieldsDto.getImage());
        }
        if(productFieldsDto.getPrice() != null) {
            product.setPrice(productFieldsDto.getPrice());
        }
        if(productFieldsDto.getQuantity() != null){
            product.setQuantity(productFieldsDto.getQuantity());
        }
        this.posService.saveProduct(product);
        return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> showProductByName(String productName) {
        List<Product> products = this.posService.findProductsByName(productName);
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(productMapper.toProductDtos(products), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> searchProductByName(String productName) {
        List<Product> products = this.posService.findProductsByName(productName);
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(productMapper.toProductDtos(products), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> patchProduct(Long productId, PatchProductRequestDto patchProductRequestDto) {
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(patchProductRequestDto.getName() != null) {
            product.setName(patchProductRequestDto.getName());
        }
        if(patchProductRequestDto.getImage() != null) {
            product.setImage(patchProductRequestDto.getImage());
        }
        if(patchProductRequestDto.getPrice() != null) {
            product.setPrice(patchProductRequestDto.getPrice());
        }
        if(patchProductRequestDto.getQuantity() != null){
            product.setQuantity(patchProductRequestDto.getQuantity());
        }
        this.posService.saveProduct(product);
        return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
    }

}
