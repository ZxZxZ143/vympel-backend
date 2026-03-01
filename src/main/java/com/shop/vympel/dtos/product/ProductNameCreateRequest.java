package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductNameCreateRequest {
    @NotNull
    private String name_kz;

    @NotNull
    private String name_ru;

    @NotNull
    private String name_en;
}
