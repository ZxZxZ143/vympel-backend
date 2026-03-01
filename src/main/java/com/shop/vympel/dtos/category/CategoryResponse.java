package com.shop.vympel.dtos.category;

import lombok.Data;

@Data
public class CategoryResponse {
    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
}
