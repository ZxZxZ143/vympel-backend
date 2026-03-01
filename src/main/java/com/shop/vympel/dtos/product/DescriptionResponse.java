package com.shop.vympel.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DescriptionResponse {
    private String shortText;
    private String title;
    private String content;
}
