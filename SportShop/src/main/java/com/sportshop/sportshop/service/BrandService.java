package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.response.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    // View all Brand
    public List<BrandResponse> getAllBrand();

    // View By Id
    public BrandResponse getBrandById(Long brandId);
}
