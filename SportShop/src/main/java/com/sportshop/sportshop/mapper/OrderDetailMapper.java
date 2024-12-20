package com.sportshop.sportshop.mapper;

import org.mapstruct.Mapper;

import com.sportshop.sportshop.dto.response.OrderDetailResponse;
import com.sportshop.sportshop.entity.OrderDetailEntity;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailResponse toOrderDetailResponse(OrderDetailEntity order);
}
