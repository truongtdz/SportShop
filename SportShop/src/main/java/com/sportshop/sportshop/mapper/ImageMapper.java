package com.sportshop.sportshop.mapper;

import com.sportshop.sportshop.dto.response.ImageResponse;
import com.sportshop.sportshop.entity.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponse toImageResponse(ImageEntity imageEntity);
}
