package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.response.ImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {
    // View All Image of Product By productId
    List<ImageResponse> getImageByProductId(Long productId);

    // Create new Image
    void createImage(MultipartFile file, Long productId);

    void createImage(List<MultipartFile> files, Long productId);

    // Delete Image
    void deleteImage(Long imageId);
}
