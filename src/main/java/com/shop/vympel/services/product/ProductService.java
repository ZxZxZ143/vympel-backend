package com.shop.vympel.services.product;

import com.shop.vympel.dtos.product.ProductCreateRequest;
import com.shop.vympel.dtos.product.ProductResponse;
import com.shop.vympel.dtos.product.ProductUpdateRequest;
import com.shop.vympel.enums.Language;

import java.util.List;

public interface ProductService {
    public ProductResponse create(ProductCreateRequest req);

    public ProductResponse update(ProductUpdateRequest req);

    public Boolean delete(Long id);

    public ProductResponse get(Long id, Language language);

    public List<ProductResponse> getAll();

    public ProductResponse archive(Long id);
}
