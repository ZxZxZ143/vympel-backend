package com.shop.vympel.mappers;

import com.shop.vympel.db.entity.product.Category;
import com.shop.vympel.dtos.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "parentId", source = "category.id")
    CategoryResponse toResponse(Category category);
}
