package com.sportshop.sportshop.service;

import com.sportshop.sportshop.entity.CartEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    // View Cart By ID User
    public List<CartEntity> getCartByUserId(Long userId) ;

    // Add Cart
    public CartEntity addProduct(Long userId, Long productId);


}
