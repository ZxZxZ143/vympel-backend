package com.shop.vympel.services.watchDetail;

import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.entity.product.WatchDetail;
import com.shop.vympel.db.repositories.WatchDetailRepository;
import com.shop.vympel.dtos.product.WatchDetailCreateRequest;
import com.shop.vympel.dtos.product.WatchDetailResponse;
import com.shop.vympel.mappers.product.WatchDetailMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WatchDetailServiceImpl implements WatchDetailService {
    private final WatchDetailRepository watchDetailRepository;
    private final WatchDetailMapper watchDetailMapper;

    @Override
    @Transactional()
    public WatchDetailResponse getWatchDetailById(Long id) {
        return watchDetailMapper
                .toResponse(
                        watchDetailRepository.findByProduct_Id(id)
                                .orElseThrow(() -> new IllegalArgumentException("WatchDetail with id " + id + " not found!"))
                );
    }

    @Override
    @Transactional
    public WatchDetail create(WatchDetailCreateRequest watchDetailCreateRequest, Product product) {
        WatchDetail watchDetail = watchDetailMapper.toEntity(watchDetailCreateRequest);
        watchDetail.setProduct(product);
        return watchDetailRepository.save(watchDetail);
    }
}
