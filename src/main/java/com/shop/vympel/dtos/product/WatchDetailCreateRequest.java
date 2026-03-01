package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WatchDetailCreateRequest {

    @NotNull
    private Long mechanismId;
    @NotNull
    private Long genderId;

    @NotNull
    private Long caseMaterialId;
    @NotNull
    private Long strapMaterialId;
    @NotNull
    private Long glassTypeId;

    @NotNull
    private Integer caseSizeMm;

    @Size(max = 50)
    private String waterResistance;

    private Long stoneInlayId;
}