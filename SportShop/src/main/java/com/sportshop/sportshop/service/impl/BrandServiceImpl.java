package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.BrandRequest;
import com.sportshop.sportshop.dto.response.BrandResponse;
import com.sportshop.sportshop.entity.BrandEntity;
import com.sportshop.sportshop.mapper.BrandMapper;
import com.sportshop.sportshop.repository.BrandRepository;
import com.sportshop.sportshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<BrandResponse> getAllBrand() {
        List<BrandResponse> brands = new ArrayList<>();
        for(BrandEntity brand : brandRepository.findAll()){
            brands.add(brandMapper.toBrandResponse(brand));
        }
        return brands;
    }

    @Override
    public BrandResponse getBrandById(Long brandId) {
        return brandMapper.toBrandResponse(brandRepository.getBrandById(brandId));
    }

    @Override
    public void createBrand(BrandRequest request) {
        BrandEntity newBrand = new BrandEntity();

        newBrand.setName(request.getName().toUpperCase());

        brandRepository.save(newBrand);
    }

    @Override
    public void deleteBrand(Long brandId){
        brandRepository.deleteById(brandId);
    }
}
