package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.features.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
