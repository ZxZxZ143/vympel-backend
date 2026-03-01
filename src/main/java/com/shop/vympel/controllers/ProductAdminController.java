package com.shop.vympel.controllers;

import com.shop.vympel.dtos.product.ProductCreateRequest;
import com.shop.vympel.dtos.product.ProductResponse;
import com.shop.vympel.services.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/product")
@AllArgsConstructor
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest productCreateRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(productCreateRequest));
    }
}
