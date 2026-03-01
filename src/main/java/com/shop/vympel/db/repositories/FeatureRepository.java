package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.features.WatchFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<WatchFeature, Long> {
}
