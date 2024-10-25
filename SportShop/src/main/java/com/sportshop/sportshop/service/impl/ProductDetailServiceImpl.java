package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.response.ProductDetailResponse;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.entity.ProductDetailEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.ProductDetailMapper;
import com.sportshop.sportshop.repository.ProductDetailRepository;
import com.sportshop.sportshop.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public List<ProductDetailResponse> getListProductDetailByProductId(Long productId) {
        List<ProductDetailResponse> productDetails = new ArrayList<>();

        for(ProductDetailEntity productDetail : productDetailRepository.findByProduct_Id(productId)){
            productDetails.add(productDetailMapper.toProductResponse(productDetail));
        }

        return  productDetails;
    }
}
