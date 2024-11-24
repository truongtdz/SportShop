package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ModelAndView homePage(HttpSession session) {
        ModelAndView mav = new ModelAndView("web/home");

        mav.addObject("products", productService.getAllProducts());
        mav.addObject("productSales", productService.getProductSale());
        mav.addObject("productDates", productService.getProductNewest());
        mav.addObject("quantityProduct", productService.getProductNewest().size());
        // Lấy thông tin người dùng từ Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            UserEntity userEntity = userService.getUserByUsername(userDetails.getUsername());
            mav.addObject("user", userEntity);
        }

        return mav;
    }

    @GetMapping("/contact")
    public ModelAndView getContact() {
        return new ModelAndView("web/contact");
    }

    @GetMapping("/introduce")
    public ModelAndView getIntroduce() {
        return new ModelAndView("web/introduce");
    }
    
}