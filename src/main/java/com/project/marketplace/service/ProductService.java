package com.project.marketplace.service;

import com.project.marketplace.dto.ProductDto;
import com.project.marketplace.exception.EntityNotFoundException;
import com.project.marketplace.model.Product;
import com.project.marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Class that contains methods for working with Products
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductDto getProductById(Long productId) {
        log.info("get Product with id{}", productId);
        Product product = productRepository.getProductsById(productId);
        if (product == null) {
            throw new EntityNotFoundException(format("Product with id %s not found", productId));
        }
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public List<ProductDto> getAllProductsDto() {
        log.info("get all Products");
        List<Product> productList = productRepository.findAll();
        System.out.println(productList);
        return productRepository.findAll()
                .stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());

    }

    public ProductDto updateProduct(String name, ProductDto productDto) {
        log.info("update Product with name{}", name);
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public void deleteProduct(long productId) {
        log.info("delete Product with id{} ", productId);
        Product product = productRepository.getProductsById(productId);
        if (product == null) {
            throw new EntityNotFoundException(format("Product with id %s not found", productId));
        }
        productRepository.delete(product);
    }

    public ProductDto addNewProduct(ProductDto productDto) {
        log.info("create new Product with name{} ", productDto.getName());
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    private ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .user(product.getUser())
                .build();
    }

}
