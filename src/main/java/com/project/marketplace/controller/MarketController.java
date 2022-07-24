package com.project.marketplace.controller;

import com.project.marketplace.dto.ProductDto;
import com.project.marketplace.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;
    @GetMapping(value = "/")
    public String getUserHello() {
        return "Hello, user!";
    }

    @GetMapping(value = "/shop/{userId}/products")
    public List<ProductDto> getUserProducts(@PathVariable Long userId) {
        return marketService.getUserProducts(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/shop/{userId}/{productId}")
    public ProductDto addProductUser(@PathVariable Long userId, @PathVariable Long productId) {
        return marketService.addProductUser(userId, productId);
    }





}
