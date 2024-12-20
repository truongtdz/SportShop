package com.sportshop.sportshop.service;

import org.springframework.stereotype.Service;

import com.sportshop.sportshop.dto.response.DailyRevenue;
import com.sportshop.sportshop.dto.response.OrderResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.StatusOrderEnum;

import java.time.LocalDate;
import java.util.List;

@Service
public interface OrderService {
    // View count Order
    Long getCount();

    Long getIncrease();

    // View all Order
    List<OrderResponse> getAllOrder();

    // Get Order By orderId;
    OrderResponse getOrderById(Long orderId);

    // AddOrder
    Long AddOrder(UserEntity user);

    // Update ToTal Price
    void UpdateToTalPrice(Long orderId);

    // Get Order By UserId
    List<OrderResponse> historyBuy(Long userId);

    // Update Status Order
    void updateStatusOrder(Long orderId, StatusOrderEnum status);

    List<DailyRevenue> getRevenueByDay(LocalDate startDate, LocalDate endDate);

}