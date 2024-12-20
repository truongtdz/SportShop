package com.sportshop.sportshop.service;

import com.sportshop.sportshop.entity.CartEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    // Get All Cart
    List<CartEntity> getCart(Long userId);

    // Find Count By UserId
    Long getCountByUserId(Long userId);

    // Add Product to Cart
    boolean addCart(Long userId, Long productId);

    // Remove Product
    void removeProductToCart(Long userId, Long productId);

    // Delete Cart When CheckOut
    void removeCartByUserId(Long userId);

    // Replace quantity product
    boolean replaceQuantityProduct(Long userId, Long productId, Long quantityReplace);
}
