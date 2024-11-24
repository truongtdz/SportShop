package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.repository.CartRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.repository.UserRepository;
import com.sportshop.sportshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    // View Cart By ID User
    @Override
    public List<CartEntity> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Add Cart
    @Override
    public CartEntity addProduct(Long userId, Long productId){
        CartEntity cartEntity = new CartEntity();

        cartEntity.setUser(userRepository.findById(userId).get());
        cartEntity.setProduct(productRepository.findById(productId).get());

        cartRepository.save(cartEntity);
        return cartEntity;
    }
}
