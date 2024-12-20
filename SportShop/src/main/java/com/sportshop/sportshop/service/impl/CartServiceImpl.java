package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.repository.CartRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.repository.UserRepository;
import com.sportshop.sportshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<CartEntity> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Long getCountByUserId(Long userId) {
        return Long.valueOf(cartRepository.findByUserId(userId).size());
    }

    @Override
    public boolean addCart(Long userId, Long productId){
        if(cartRepository.findByUserIdAndProductId(userId, productId) != null) {
            return false;
        }
        CartEntity newCart = CartEntity.builder()
                .user((userRepository.findById(userId)).get())
                .product(productRepository.findById(productId).get())
                .quantity(Long.valueOf("1"))
                .build();

        cartRepository.save(newCart);
        return true;
    }

    @Override
    @Transactional
    public void removeProductToCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    @Transactional
    public void removeCartByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Override
    public boolean replaceQuantityProduct(Long userId, Long productId, Long quantityReplace){
        CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId);
        ProductEntity product = productRepository.findById(productId).get();

        if(product.getQuantity() < (cart.getQuantity() + quantityReplace)){
            return false;
        }
        cart.setQuantity(cart.getQuantity() + quantityReplace);

        cartRepository.save(cart);

        return true;
    }
}
