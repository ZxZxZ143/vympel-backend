package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.product.ProductCategory;
import com.shop.vympel.db.entity.product.ProductCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId> {
    Optional<ProductCategory> getByProductId(Long productId);
}
