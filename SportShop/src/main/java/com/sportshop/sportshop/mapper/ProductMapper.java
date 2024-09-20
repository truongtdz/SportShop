package com.sportshop.sportshop.mapper;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.request.ProductResponse;
import com.sportshop.sportshop.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(ProductEntity productEntity);
    ProductEntity toProductEntity(ProductRequest productRequest);
}
