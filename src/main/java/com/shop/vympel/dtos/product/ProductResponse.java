package com.shop.vympel.dtos.product;

import com.shop.vympel.dtos.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String sku;
    private String name;

    private Integer price;
    private String status;
    private String productType;

    private CategoryResponse category;

    private Long brandId;
    private String brandName;

    private Long collectionId;
    private String collectionName;

    private DescriptionResponse description;

    private WatchDetailResponse watchDetails;
}