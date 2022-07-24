package com.project.marketplace.repository;

import com.project.marketplace.model.Product;
import com.project.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {
    Product getProductsById(long productId);

    List<Product> findAll();

    void delete(Product product);

    Product save(Product product);

    List<Product> getProductsByUser(User user);
}
