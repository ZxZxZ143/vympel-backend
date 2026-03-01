package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.features.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findByBrand_IdAndCode(Long brandId, String code);
}
