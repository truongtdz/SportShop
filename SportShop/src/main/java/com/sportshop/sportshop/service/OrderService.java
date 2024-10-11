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
public interface OrderService {

    // View order Quantity
    public String countOrders() ;

    // View All Order
    public List<OrderResponse> getALlOrders() ;

    // View Order ny Id
    public OrderResponse getOrderById(Long orderId) ;

    // View Bill Order by Id
    public List<BillResponse> getBillById(Long orderId) ;

}
