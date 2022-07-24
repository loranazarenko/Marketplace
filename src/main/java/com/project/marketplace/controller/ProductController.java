package com.project.marketplace.controller;

import com.project.marketplace.dto.ProductDto;
import com.project.marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for working with products
 */
@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProductsDto();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{name}")
    public ProductDto editProduct(@PathVariable("name") String name,
                                  @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(name, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }
}
