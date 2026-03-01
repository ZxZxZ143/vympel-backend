package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
