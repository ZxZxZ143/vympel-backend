package com.shop.vympel.controllers;

import com.shop.vympel.services.objectStorage.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/file")
@RequiredArgsConstructor
public class FileController {
    private final ObjectStorageService objectStorageService;

    @PostMapping("/product/{productId}/upload/image")
    public ResponseEntity<List<String>> uploadImages(
            @RequestPart("files") List<MultipartFile> file,
            @PathVariable Long productId) throws IOException {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectStorageService.uploadProductImage(file, productId));
    }
}
