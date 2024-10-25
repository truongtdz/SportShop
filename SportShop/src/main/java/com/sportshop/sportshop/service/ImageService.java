package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.ImageRequest;
import com.sportshop.sportshop.dto.response.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    // View All Image of Product By productId
    List<ImageResponse> getImageByProductId(Long productId);

    // Create new Image
    void createImage(ImageRequest request);
}
