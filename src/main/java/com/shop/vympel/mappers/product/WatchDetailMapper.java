package com.shop.vympel.mappers.product;

import com.shop.vympel.db.entity.product.WatchDetail;
import com.shop.vympel.dtos.product.WatchDetailCreateRequest;
import com.shop.vympel.dtos.product.WatchDetailResponse;
import com.shop.vympel.dtos.product.WatchDetailUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityReferenceMapper.class)
public interface WatchDetailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "mechanism", source = "mechanismId", qualifiedByName = "toMechanism")
    @Mapping(target = "gender", source = "genderId", qualifiedByName = "toGender")
    @Mapping(target = "caseMaterial", source = "caseMaterialId", qualifiedByName = "toMaterial")
    @Mapping(target = "strapMaterial", source = "strapMaterialId", qualifiedByName = "toMaterial")
    @Mapping(target = "glassType", source = "glassTypeId", qualifiedByName = "toGlassType")
    @Mapping(target = "stoneInlay", source = "stoneInlayId", qualifiedByName = "toStoneInlay")
    WatchDetail toEntity(WatchDetailCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)

    @Mapping(target = "mechanism", source = "mechanismId", qualifiedByName = "toMechanism")
    @Mapping(target = "gender", source = "genderId", qualifiedByName = "toGender")
    @Mapping(target = "caseMaterial", source = "caseMaterialId", qualifiedByName = "toMaterial")
    @Mapping(target = "strapMaterial", source = "strapMaterialId", qualifiedByName = "toMaterial")
    @Mapping(target = "glassType", source = "glassTypeId", qualifiedByName = "toGlassType")
    @Mapping(target = "stoneInlay", source = "stoneInlayId", qualifiedByName = "toStoneInlay")
    void updateEntity(@MappingTarget WatchDetail entity, WatchDetailUpdateRequest req);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "mechanismId", source = "mechanism.id")
    @Mapping(target = "mechanismName", source = "mechanism.code")
    @Mapping(target = "genderId", source = "gender.id")
    @Mapping(target = "genderName", source = "gender.code")
    @Mapping(target = "caseMaterialId", source = "caseMaterial.id")
    @Mapping(target = "caseMaterialName", source = "caseMaterial.code")
    @Mapping(target = "strapMaterialId", source = "strapMaterial.id")
    @Mapping(target = "strapMaterialName", source = "strapMaterial.code")
    @Mapping(target = "glassTypeId", source = "glassType.id")
    @Mapping(target = "glassTypeName", source = "glassType.code")
    @Mapping(target = "stoneInlayId", source = "stoneInlay.id")
    @Mapping(target = "stoneInlayName", source = "stoneInlay.code")
    WatchDetailResponse toResponse(WatchDetail entity);
}