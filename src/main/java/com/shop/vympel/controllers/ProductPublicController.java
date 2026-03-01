package com.shop.vympel.controllers;

import com.shop.vympel.dtos.product.ProductResponse;
import com.shop.vympel.enums.Language;
import com.shop.vympel.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/product")
@RequiredArgsConstructor
public class ProductPublicController {
    private final ProductService productService;

    @GetMapping("/{lang}/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id, @PathVariable Language lang) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.get(id, lang));
    }
}
