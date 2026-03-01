package com.shop.vympel.services.category;

import com.shop.vympel.db.entity.i18n.CategoryI18n;
import com.shop.vympel.db.entity.i18n.CategoryI18nId;
import com.shop.vympel.db.entity.product.Category;
import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.entity.product.ProductCategory;
import com.shop.vympel.db.entity.product.ProductCategoryId;
import com.shop.vympel.db.repositories.CategoryI18Repository;
import com.shop.vympel.db.repositories.CategoryRepository;
import com.shop.vympel.db.repositories.ProductCategoryRepository;
import com.shop.vympel.dtos.category.CategoryResponse;
import com.shop.vympel.enums.Language;
import com.shop.vympel.mappers.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryI18Repository categoryI18Repository;
    private final CategoryMapper categoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<CategoryResponse> getAll() {

        return List.of();
    }

    @Override
    @Transactional
    public CategoryResponse getById(Long id, Language lang) throws IllegalArgumentException {
        CategoryI18nId categoryI18nId = new CategoryI18nId(id, lang.getValue());

        CategoryI18n categoryI18n = categoryI18Repository.findById(categoryI18nId)
                .orElseThrow(() -> new IllegalArgumentException("CategoryI18nId not found!"));

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found!"));

        CategoryResponse categoryResponse = categoryMapper.toResponse(category);
        categoryResponse.setCategoryName(categoryI18n.getName());
        return categoryResponse;
    }

    @Override
    @Transactional
    public void linkWithProduct(Long categoryId, Product product) throws IllegalArgumentException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Found"));

        ProductCategory productCategory = new ProductCategory();

        ProductCategoryId productCategoryId = new ProductCategoryId();
        productCategoryId.setProductId(product.getId());
        productCategoryId.setCategoryId(categoryId);

        productCategory.setProduct(product);
        productCategory.setCategory(category);
        productCategory.setId(productCategoryId);

        productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public CategoryResponse getByProductId(Long productId, Language language) throws IllegalArgumentException {
        ProductCategory productCategory = productCategoryRepository
                .getByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("ProductCategory not found!"));

        return getById(productCategory.getCategory().getId(), language);
    }
}
