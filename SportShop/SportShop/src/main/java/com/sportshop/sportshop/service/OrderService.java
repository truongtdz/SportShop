package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.response.BillResponse;
import com.sportshop.sportshop.dto.response.OrderResponse;
import com.sportshop.sportshop.entity.OrderEntity;
import com.sportshop.sportshop.mapper.OrderMapper;
import com.sportshop.sportshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    // View order Quantity
    public String countOrders() {
        return String.valueOf(orderRepository.count());
    }

    // View All Order
    public List<OrderResponse> getALlOrders() {
        List<OrderResponse> orders = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll();
        for(OrderEntity orderEntity : orderEntities) {
            orders.add(orderMapper.toOrderResponse(orderEntity));
        }
        return orders;
    }

    // View Order ny Id
    public OrderResponse getOrderById(Long orderId) {
        return orderMapper.toOrderResponse(orderRepository.findById(String.valueOf(orderId)).get());
    }

    // View Bill Order by Id
    public List<BillResponse> getBillById(Long orderId) {
        return orderRepository.getBillByOrderId(String.valueOf(orderId));
    }


}
