package com.project.marketplace.service;

import com.project.marketplace.dto.ProductDto;
import com.project.marketplace.exception.EntityNotFoundException;
import com.project.marketplace.exception.MoneyNotEnoughException;
import com.project.marketplace.model.Product;
import com.project.marketplace.model.User;
import com.project.marketplace.repository.ProductRepository;
import com.project.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<ProductDto> getUserProducts(Long userId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new EntityNotFoundException(format("User with id %s not found", userId));
        }

        List<Product> products = productRepository.getProductsByUser(user);
        return ProductMapper.INSTANCE.mapProductDtos(products);
    }

    public ProductDto addProductUser(Long userId, Long productId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new EntityNotFoundException(format("User with id %s not found", userId));
        }
        Product newProduct = productRepository.getProductsById(productId);
        newProduct.setUser(user);
        productRepository.save(newProduct);
        double restMoney = user.getMoney() - newProduct.getPrice();
        if (restMoney < 0) {
            throw new MoneyNotEnoughException(format("For user with id %s money is not enough - get the result %s", userId, restMoney));
        } else {
            user.setMoney(restMoney);
            userRepository.save(user);
        }

        return ProductMapper.INSTANCE.mapProductDto(newProduct);
    }
}
