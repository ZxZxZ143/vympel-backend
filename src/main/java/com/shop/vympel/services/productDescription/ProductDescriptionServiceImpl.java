package com.shop.vympel.services.productDescription;

import com.shop.vympel.db.entity.features.ProductDescription;
import com.shop.vympel.db.entity.features.ProductDescriptionI18n;
import com.shop.vympel.db.entity.features.ProductDescriptionI18nId;
import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.repositories.ProductDescriptionI18Repository;
import com.shop.vympel.db.repositories.ProductDescriptionRepository;
import com.shop.vympel.dtos.product.DescriptionCreateRequest;
import com.shop.vympel.dtos.product.DescriptionResponse;
import com.shop.vympel.enums.Language;
import com.shop.vympel.mappers.product.ProductDescriptionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDescriptionServiceImpl implements ProductDescriptionService {
    private final ProductDescriptionRepository productDescriptionRepository;
    private final ProductDescriptionI18Repository productDescriptionI18Repository;
    private final ProductDescriptionMapper productDescriptionMapper;

    @Override
    @Transactional
    public void addProductDescription(Product product, Language language, DescriptionCreateRequest descriptionCreateRequest) {
        ProductDescription productDescription = productDescriptionRepository.findByProductId(product.getId()).orElse(null);

        if (productDescription == null) {
            productDescription = new ProductDescription();
            productDescription.setProduct(product);
        }

        productDescriptionRepository.save(productDescription);

        ProductDescriptionI18n productDescriptionI18n = productDescriptionMapper.toEntity(descriptionCreateRequest);
        System.out.println(productDescriptionI18n);
        productDescriptionI18n.setId(
                createProductDescriptionI18nId(
                        productDescription.getId(),
                        language
                )
        );

        productDescriptionI18n.setDescription(productDescription);

        productDescriptionI18Repository.save(productDescriptionI18n);
    }

    @Override
    @Transactional
    public DescriptionResponse getDescriptionContentById(Long id, Language language) {
        return productDescriptionMapper.toResponse(
                productDescriptionI18Repository.findProductDescriptionI18nById(
                        createProductDescriptionI18nId(id, language)
                        )
                        .orElseThrow(() -> new IllegalArgumentException("no such product description"))
        );
    }

    @Override
    @Transactional
    public ProductDescription getDescriptionById(Long productId) {
        return productDescriptionRepository
                .findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("no such product description"));
    }

    private ProductDescriptionI18nId createProductDescriptionI18nId(Long productDescriptionId, Language language) {
        ProductDescriptionI18nId productDescriptionI18nId = new ProductDescriptionI18nId();
        productDescriptionI18nId.setDescriptionId(productDescriptionId);
        productDescriptionI18nId.setLang(language.getValue());
        return productDescriptionI18nId;
    }
}
