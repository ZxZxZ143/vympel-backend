package com.shop.vympel.services.productDescription;

import com.shop.vympel.db.entity.features.ProductDescription;
import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.dtos.product.DescriptionCreateRequest;
import com.shop.vympel.dtos.product.DescriptionResponse;
import com.shop.vympel.enums.Language;

public interface ProductDescriptionService {
    void addProductDescription(Product product, Language language, DescriptionCreateRequest descriptionCreateRequest);
    DescriptionResponse getDescriptionContentById(Long id, Language language);

    ProductDescription getDescriptionById(Long productId);
}
