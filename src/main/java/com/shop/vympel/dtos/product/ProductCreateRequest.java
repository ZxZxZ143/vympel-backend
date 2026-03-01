package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductCreateRequest {

    @NotNull
    private ProductNameCreateRequest productName;

    @NotNull
    private Integer price;

    @Size(max = 20)
    @NotNull
    private String status;

    @Size(max = 30)
    @NotNull
    private String productType;

    @NotNull
    private Long brandId;

    private Long collectionId;

    @NotNull
    private Long categoryId;

    @NotNull
    private DescriptionCreateRequest description;

    @NotNull
    private WatchDetailCreateRequest watchDetails;
}
