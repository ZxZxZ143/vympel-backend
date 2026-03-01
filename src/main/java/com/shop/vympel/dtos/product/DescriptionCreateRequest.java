package com.shop.vympel.dtos.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DescriptionCreateRequest {
    @NotNull
    private String desc;
}
