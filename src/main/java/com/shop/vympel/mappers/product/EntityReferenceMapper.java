package com.shop.vympel.mappers.product;

import com.shop.vympel.db.entity.features.*;
import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.repositories.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityReferenceMapper {

    private final WatchMechanismRepository mechanismRepository;
    private final GenderRepository genderRepository;
    private final MaterialRepository materialRepository;
    private final GlassTypeRepository glassTypeRepository;
    private final StoneInlayRepository stoneInlayRepository;
    private final BrandRepository brandRepository;
    private final CollectionRepository collectionRepository;

    @Named("toMechanism")
    public WatchMechanism toMechanism(Long id) {
        return id == null ? null : mechanismRepository.getReferenceById(id);
    }

    @Named("toGender")
    public Gender toGender(Long id) {
        return id == null ? null : genderRepository.getReferenceById(id);
    }

    @Named("toMaterial")
    public Material toMaterial(Long id) {
        return id == null ? null : materialRepository.getReferenceById(id);
    }

    @Named("toGlassType")
    public GlassType toGlassType(Long id) {
        return id == null ? null : glassTypeRepository.getReferenceById(id);
    }

    @Named("toStoneInlay")
    public StoneInlay toStoneInlay(Long id) {
        return id == null ? null : stoneInlayRepository.getReferenceById(id);
    }

    @Named("toBrand")
    public Brand toBrand(Long id) {
        return id == null ? null : brandRepository.getReferenceById(id);
    }

    @Named("toCollection")
    public Collection toCollection(Long id) {
        return id == null ? null : collectionRepository.getReferenceById(id);
    }
}