package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.ImageRequest;
import com.sportshop.sportshop.dto.response.ImageResponse;
import com.sportshop.sportshop.entity.ImageEntity;
import com.sportshop.sportshop.mapper.ImageMapper;
import com.sportshop.sportshop.repository.ImageRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageMapper imageMapper;

    @Override
    public List<ImageResponse> getImageByProductId(Long productId) {
        List<ImageResponse> images = new ArrayList<>();

        for(ImageEntity image : imageRepository.findByProduct_Id(productId)){
            images.add(imageMapper.toImageResponse(image));
        }

        return  images;
    }

    @Override
    public void createImage(ImageRequest request){
        ImageEntity newImage = new ImageEntity();

        newImage.setProduct(productRepository.findById(request.getProductId()));
        newImage.setImageLink(request.getImageLink());

        imageRepository.save(newImage);
    }
}
