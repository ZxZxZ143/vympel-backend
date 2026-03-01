package com.shop.vympel.services.watchDetail;

import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.entity.product.WatchDetail;
import com.shop.vympel.dtos.product.WatchDetailCreateRequest;
import com.shop.vympel.dtos.product.WatchDetailResponse;

public interface WatchDetailService {
    WatchDetailResponse getWatchDetailById(Long id);
    WatchDetail create(WatchDetailCreateRequest watchDetailCreateRequest, Product product);
}
