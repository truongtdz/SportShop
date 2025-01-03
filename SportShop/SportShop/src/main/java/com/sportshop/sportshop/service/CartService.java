package com.sportshop.sportshop.service;

import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // View Cart
    public CartEntity getCarts(Long userId) {
        return userRepository.findByUserId(userId);
    }

    // Add Cart
    public CartEntity addCart(Long userId, Long productId){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser(userRepository.findById(userId));
        cartEntity.setProduct(productRepository.findById(productId));

        return cartEntity;
    }
}
