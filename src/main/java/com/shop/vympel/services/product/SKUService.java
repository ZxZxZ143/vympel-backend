package com.shop.vympel.services.product;

import com.shop.vympel.db.repositories.*;
import com.shop.vympel.dtos.product.ProductCreateRequest;
import com.shop.vympel.dtos.product.WatchDetailCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SKUService {

    private final BrandRepository brandRepository;
    private final CollectionRepository collectionRepository;
    private final CategoryRepository categoryRepository;

    private final GenderRepository genderRepository;
    private final WatchMechanismRepository watchMechanismRepository;
    private final StoneInlayRepository stoneInlayRepository;
    private final GlassTypeRepository glassTypeRepository;
    private final MaterialRepository materialRepository;

    @Transactional(readOnly = true)
    public String skuGen(ProductCreateRequest product) {
        if (product == null) throw new IllegalArgumentException("product is null");

        WatchDetailCreateRequest w = product.getWatchDetails();
        if (w == null) throw new IllegalArgumentException("watchDetails is required to generate watch SKU");

        String brandPart = brandRepository.findById(product.getBrandId())
                .map(b -> safePart(b.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Brand not found: " + product.getBrandId()));

        String collectionPart = product.getCollectionId() == null
                ? "NC"
                : collectionRepository.findById(product.getCollectionId())
                .map(c -> safePart(firstNonBlank(c.getCode(), c.getName())))
                .orElseThrow(() -> new IllegalArgumentException("Collection not found: " + product.getCollectionId()));

        String categoryPart = product.getCategoryId() == null
                ? "NCAT"
                : categoryRepository.findById(product.getCategoryId())
                .map(c -> safePart(firstNonBlank(c.getCode(), c.getCode())))
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + product.getCategoryId()));

        String genderPart = genderRepository.findById(w.getGenderId())
                .map(g -> safePart(g.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Gender not found: " + w.getGenderId()));

        String mechanismPart = watchMechanismRepository.findById(w.getMechanismId())
                .map(m -> safePart(m.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Mechanism not found: " + w.getMechanismId()));

        String stonePart = (w.getStoneInlayId() == null)
                ? "NST"
                : stoneInlayRepository.findById(w.getStoneInlayId())
                .map(s -> safePart(s.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Stone inlay not found: " + w.getStoneInlayId()));

        String glassPart = glassTypeRepository.findById(w.getGlassTypeId())
                .map(g -> safePart(g.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Glass type not found: " + w.getGlassTypeId()));

        String caseMatPart = materialRepository.findById(w.getCaseMaterialId())
                .map(m -> safePart(m.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Case material not found: " + w.getCaseMaterialId()));

        String strapMatPart = materialRepository.findById(w.getStrapMaterialId())
                .map(m -> safePart(m.getCode()))
                .orElseThrow(() -> new IllegalArgumentException("Strap material not found: " + w.getStrapMaterialId()));

        String sizePart = formatCaseSize(w.getCaseSizeMm());
        String wrPart = formatWaterResistance(w.getWaterResistance());

        return String.join("-",
                "SKU",
                brandPart,
                collectionPart,
                categoryPart,
                genderPart,
                mechanismPart,
                stonePart,
                glassPart,
                caseMatPart,
                strapMatPart,
                sizePart,
                wrPart
        );
    }

    private static String safePart(String raw) {
        if (raw == null || raw.isBlank()) return "NA";
        return raw.trim()
                .toUpperCase()
                .replaceAll("\\s+", "")
                .replaceAll("[^A-Z0-9\\-]", "");
    }

    private static String firstNonBlank(String a, String b) {
        if (a != null && !a.isBlank()) return a;
        if (b != null && !b.isBlank()) return b;
        return null;
    }

    private static String formatCaseSize(Integer sizeMm) {
        if (sizeMm == null) return "NSZ";
        BigDecimal b = new BigDecimal(sizeMm);
        BigDecimal scaled = b.multiply(BigDecimal.TEN);
        return "SZ" + scaled.stripTrailingZeros().toPlainString().replace(".", "");
    }

    private static String formatWaterResistance(String wr) {
        if (wr == null || wr.isBlank()) return "NWR";
        return safePart(wr);
    }
}

