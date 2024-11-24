package com.sportshop.sportshop.mapper;

import com.sportshop.sportshop.dto.response.BrandResponse;
import com.sportshop.sportshop.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toBrandResponse(BrandEntity brandEntity);
}
