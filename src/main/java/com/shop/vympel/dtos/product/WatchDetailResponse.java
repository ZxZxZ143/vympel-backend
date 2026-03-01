package com.shop.vympel.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WatchDetailResponse {

    private Long productId;

    private Long mechanismId;
    private String mechanismName;

    private Long genderId;
    private String genderName;

    private Long caseMaterialId;
    private String caseMaterialName;

    private Long strapMaterialId;
    private String strapMaterialName;

    private Long glassTypeId;
    private String glassTypeName;

    private Integer caseSizeMm;
    private String waterResistance;

    private Long stoneInlayId;
    private String stoneInlayName;
}
