package com.shop.vympel.services.category;

import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.dtos.category.CategoryResponse;
import com.shop.vympel.enums.Language;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id, Language lang) throws IllegalArgumentException;

    void linkWithProduct(Long categoryId, Product product);

    CategoryResponse getByProductId(Long productId, Language language) throws IllegalArgumentException;

}
