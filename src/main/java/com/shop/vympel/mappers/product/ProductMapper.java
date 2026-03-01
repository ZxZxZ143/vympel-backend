package com.shop.vympel.mappers.product;

import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.dtos.product.ProductCreateRequest;
import com.shop.vympel.dtos.product.ProductResponse;
import com.shop.vympel.dtos.product.ProductUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = EntityReferenceMapper.class)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku",  ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    @Mapping(target = "collection", source = "collectionId", qualifiedByName = "toCollection")
    Product toEntity(ProductCreateRequest req, @Context EntityReferenceMapper ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku",  ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    @Mapping(target = "collection", source = "collectionId", qualifiedByName = "toCollection")
    void updateEntity(@MappingTarget Product entity, ProductUpdateRequest req, @Context EntityReferenceMapper ref);

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "collectionId", source = "collection.id")
    @Mapping(target = "collectionName", source = "collection.name")
    @Mapping(target = "watchDetails", ignore = true)
    ProductResponse toResponse(Product entity);

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "collectionId", source = "collection.id")
    @Mapping(target = "collectionName", source = "collection.name")
    @Mapping(target = "watchDetails", ignore = true)
    List<ProductResponse> toResponse(List<Product> entities);
}
