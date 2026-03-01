package com.shop.vympel.services.product;

import com.shop.vympel.db.entity.product.*;
import com.shop.vympel.db.repositories.*;
import com.shop.vympel.dtos.product.ProductCreateRequest;
import com.shop.vympel.dtos.product.ProductResponse;
import com.shop.vympel.dtos.product.ProductUpdateRequest;
import com.shop.vympel.enums.Language;
import com.shop.vympel.mappers.product.EntityReferenceMapper;
import com.shop.vympel.mappers.product.ProductMapper;
import com.shop.vympel.services.category.CategoryService;
import com.shop.vympel.services.productDescription.ProductDescriptionService;
import com.shop.vympel.services.productName.ProductNameService;
import com.shop.vympel.services.watchDetail.WatchDetailServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EntityReferenceMapper entityReferenceMapper;
    private final SKUService SKUService;
    private final WatchDetailServiceImpl watchDetailService;
    private final ProductDescriptionService productDescriptionService;
    private final ProductNameService productNameService;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public ProductResponse create(ProductCreateRequest req) throws IllegalArgumentException {
        String sku = SKUService.skuGen(req);
        Product product = productRepository.findProductBySku(sku).orElse(null);

        if (product != null) throw new IllegalArgumentException("Product already exists");

        Product newProduct = productMapper.toEntity(req, entityReferenceMapper);
        newProduct.setSku(sku);

        newProduct = productRepository.save(newProduct);

        categoryService.linkWithProduct(req.getCategoryId(), newProduct);

        watchDetailService.create(req.getWatchDetails(), newProduct);

        productDescriptionService.addProductDescription(newProduct, Language.RU, req.getDescription());
        productDescriptionService.addProductDescription(newProduct, Language.EN, req.getDescription());
        productDescriptionService.addProductDescription(newProduct, Language.KZ, req.getDescription());

        productNameService.createProductName(newProduct, Language.RU, req.getProductName().getName_ru());
        productNameService.createProductName(newProduct, Language.EN, req.getProductName().getName_en());
        productNameService.createProductName(newProduct, Language.KZ, req.getProductName().getName_kz());

        return productMapper.toResponse(newProduct);
    }

    @Override
    public ProductResponse update(ProductUpdateRequest req) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public ProductResponse get(Long id, Language language) throws IllegalArgumentException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ProductResponse productResponse = productMapper.toResponse(product);

        productResponse.setWatchDetails(
                watchDetailService.getWatchDetailById(product.getId())
        );


        productResponse.setDescription(
                productDescriptionService
                        .getDescriptionContentById(
                                productDescriptionService.getDescriptionById(product.getId()).getId(),
                                language
                        )
        );

        productResponse.setName(
                productNameService.getById(
                        product.getId(),
                        language
                ).getName()
        );

        productResponse.setCategory(
            categoryService.getByProductId(product.getId(), language)
        );

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAll() {
        return null;
    }

    @Override
    public ProductResponse archive(Long id) {
        return null;
    }
}
