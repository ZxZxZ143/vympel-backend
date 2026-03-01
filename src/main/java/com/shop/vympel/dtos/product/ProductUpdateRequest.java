package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateRequest {

    private Integer price;

    @Size(max = 20)
    private String status;

    @Size(max = 30)
    private String productType;

    private Long brandId;

    private Long collectionId;

    private WatchDetailUpdateRequest watchDetails;
}