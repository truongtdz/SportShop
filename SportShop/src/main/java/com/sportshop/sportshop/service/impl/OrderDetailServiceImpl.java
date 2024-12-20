package com.sportshop.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.sportshop.dto.response.OrderDetailResponse;
import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.OrderDetailEntity;
import com.sportshop.sportshop.entity.OrderEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.OrderDetailMapper;
import com.sportshop.sportshop.repository.OrderDetailRepository;
import com.sportshop.sportshop.repository.OrderRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public void AddItem(List<CartEntity> carts, Long orderId) {

        for(CartEntity cart : carts){
            OrderDetailEntity newItem = new OrderDetailEntity();

            ProductEntity product = productRepository.findById(cart.getProduct().getId()).get();

            OrderEntity order = orderRepository.findById(orderId).get();

            newItem.setQuantity(cart.getQuantity());
            newItem.setDiscount(product.getDiscount());
            newItem.setPrice(product.getPrice());
            newItem.setTotal((product.getPrice() * (100 - product.getDiscount()) / 100) * cart.getQuantity());
            newItem.setProduct(product);
            newItem.setOrder(order);

            orderDetailRepository.save(newItem);
        }

    }

    @Override
    public List<OrderDetailResponse> getItemByOrderId(Long orderId) {
        List<OrderDetailResponse> result = new ArrayList<>();

        for (OrderDetailEntity detail : orderDetailRepository.findByOrderId(orderId)){
            result.add(orderDetailMapper.toOrderDetailResponse(detail));
        }
        return result;
    }
}
