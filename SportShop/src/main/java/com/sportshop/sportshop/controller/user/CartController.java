package com.sportshop.sportshop.controller.user;

import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart/{userId}")
    public ModelAndView getCart(@PathVariable Long userId) {
        List<CartEntity> carts = cartService.getCartByUserId(userId);
        return new ModelAndView("user/cart")
                .addObject("carts", carts)
                .addObject("user", userService.getUserById(userId));
    }

    @PostMapping("/add-to-cart/{productId}")
    public ModelAndView addCart(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView("web/home");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            UserEntity user = userService.getUserByUsername(userDetails.getUsername());
            modelAndView.addObject("user", user);

            cartService.addProduct(user.getId(), productId);
        } else {
            return new ModelAndView("redirect:/login");
        }

        return modelAndView
                .addObject("message", "Success");
    }
}
