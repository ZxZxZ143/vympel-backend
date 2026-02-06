package com.shop.vympel.db.entity.i18n;

import com.shop.vympel.db.entity.features.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "brand_i18n")
public class BrandI18n {
    @EmbeddedId
    private BrandI18nId id;

    @MapsId("brandId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


}