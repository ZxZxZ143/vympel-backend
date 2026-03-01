package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WatchDetailUpdateRequest {

    private Long mechanismId;
    private Long genderId;

    private Long caseMaterialId;
    private Long strapMaterialId;
    private Long glassTypeId;

    private Integer caseSizeMm;

    @Size(max = 50)
    private String waterResistance;

    private Long stoneInlayId;
}
