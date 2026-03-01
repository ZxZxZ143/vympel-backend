package com.shop.vympel.controllers;

import com.shop.vympel.dtos.collection.CollectionCreateRequest;
import com.shop.vympel.dtos.collection.CollectionResponse;
import com.shop.vympel.services.collection.CollectionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/collection")
@RequiredArgsConstructor
public class CollectionAdminController {
    private final CollectionServiceImpl collectionService;

    @PostMapping("/create")
    public ResponseEntity<CollectionResponse> createCollection(@RequestBody CollectionCreateRequest request) {
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(collectionService.toCreate(request));
    }
}
