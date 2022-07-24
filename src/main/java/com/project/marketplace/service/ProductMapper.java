package com.project.marketplace.service;

import com.project.marketplace.dto.ProductDto;
import com.project.marketplace.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Declaring an instruction for mapstruct
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto mapProductDto(Product product);
    Product mapProduct(ProductDto productDto);
    List<ProductDto> mapProductDtos(List<Product> products);
}


