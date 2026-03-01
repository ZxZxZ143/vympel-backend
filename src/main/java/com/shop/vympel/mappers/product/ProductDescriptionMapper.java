package com.shop.vympel.mappers.product;

import com.shop.vympel.db.entity.features.ProductDescriptionI18n;
import com.shop.vympel.dtos.product.DescriptionCreateRequest;
import com.shop.vympel.dtos.product.DescriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDescriptionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "desc")
    @Mapping(target = "shortText", source = "desc")
    @Mapping(target = "contentMd", source = "desc")
    ProductDescriptionI18n toEntity(DescriptionCreateRequest descriptionCreateRequest);

    @Mapping(target = "content", source = "contentMd")
    DescriptionResponse toResponse(ProductDescriptionI18n productDescriptionI18n);
}
