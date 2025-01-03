package com.sportshop.sportshop.controller.user;

import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{userId}")
    public ModelAndView getCart(@PathVariable Long userId) {
        CartEntity cart = cartService.getCarts(userId);
        return new ModelAndView("user/cart")
                .addObject("cart", cart);
    }

    @PostMapping("/cart/{productId}")
    public ModelAndView addCart(@PathVariable Long productId, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        cartService.addCart(user.getId(), productId);
        return new ModelAndView("/web/home")
                .addObject("message", "Success");
    }
}
