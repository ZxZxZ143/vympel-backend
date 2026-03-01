package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findProductBySku(String sku);
}
