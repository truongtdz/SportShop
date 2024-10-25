package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.response.ProductDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    // View list Product Detail by ProductId
    List<ProductDetailResponse> getListProductDetailByProductId(Long productId);
}
