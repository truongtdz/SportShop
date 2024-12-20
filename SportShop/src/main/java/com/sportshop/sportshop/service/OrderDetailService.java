package com.sportshop.sportshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportshop.sportshop.dto.response.OrderDetailResponse;
import com.sportshop.sportshop.entity.CartEntity;

@Service
public interface OrderDetailService {
    // Add Item
    void AddItem(List<CartEntity> carts, Long orderId);

    // Get Item By Order Id
    List<OrderDetailResponse> getItemByOrderId(Long orderId);
} 
