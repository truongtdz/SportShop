package com.sportshop.sportshop.mapper;

import com.sportshop.sportshop.dto.response.ProductDetailResponse;
import com.sportshop.sportshop.entity.ProductDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    ProductDetailResponse toProductResponse(ProductDetailEntity productDetailEntity);
}
