package com.shop.vympel.services.objectStorage;

import com.shop.vympel.db.entity.product.Media;
import com.shop.vympel.db.entity.product.Product;
import com.shop.vympel.db.repositories.MediaRepository;
import com.shop.vympel.db.repositories.ProductRepository;
import com.shop.vympel.enums.MediaType;
import com.shop.vympel.enums.ObjectStoragePath;
import com.shop.vympel.s3.StorageProps;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class ObjectStorageService {

    private final S3Client s3;
    private final StorageProps props;
    private final MediaRepository mediaRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<String> uploadProductImage(List<MultipartFile> files, Long productId) throws IllegalArgumentException, IOException {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + productId)
        );
        List<String> keys = new ArrayList<>();
        int pos = 0;
        for (MultipartFile file : files) {
            String key = ObjectStoragePath.PRODUCT.getValue() + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            System.out.println(file.getContentType());
            PutObjectRequest uploadRequest = PutObjectRequest.builder()
                    .bucket(props.bucket())
                    .key(key)
                    .contentType(file.getContentType())
                    .metadata(Map.of(
                            "originalName", safeName(file.getOriginalFilename())
                    ))
                    .build();

            s3.putObject(uploadRequest, RequestBody.fromBytes(file.getBytes()));

            Media newMedia = new Media();
            newMedia.setProduct(product);
            newMedia.setPosition(pos++);
            newMedia.setType(MediaType.IMAGE.getCode());
            newMedia.setUrl(key);
            mediaRepository.save(newMedia);

            keys.add(key);
        }

        return keys;
    }

    public byte[] download(String key) {
        GetObjectRequest req = GetObjectRequest.builder()
                .bucket(props.bucket())
                .key(key)
                .build();

        return s3.getObjectAsBytes(req).asByteArray();
    }

    public void delete(String key) {
        s3.deleteObject(DeleteObjectRequest.builder()
                .bucket(props.bucket())
                .key(key)
                .build());
    }

    private String safeName(String name) {
        if (name == null) return "file";
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
