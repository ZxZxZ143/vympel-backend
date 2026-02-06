package com.shop.vympel.db.entity.product;

import com.shop.vympel.db.entity.features.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "watch_details")
public class WatchDetail {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mechanism_id", nullable = false)
    private WatchMechanism mechanism;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_material_id", nullable = false)
    private Material caseMaterial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "strap_material_id", nullable = false)
    private Material strapMaterial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "glass_type_id", nullable = false)
    private GlassType glassType;

    @NotNull
    @Column(name = "case_size_mm", nullable = false, precision = 4, scale = 1)
    private BigDecimal caseSizeMm;

    @Size(max = 50)
    @Column(name = "water_resistance", length = 50)
    private String waterResistance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stone_inlay_id")
    private StoneInlay stoneInlay;


}