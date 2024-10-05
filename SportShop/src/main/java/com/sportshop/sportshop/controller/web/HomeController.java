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
        ModelAndView modelAndView = new ModelAndView("web/home");
        modelAndView.addObject("products", productService.getAllProducts());

        // Kiểm tra user trong session như bạn đang làm
        Object user = session.getAttribute("user");
        if (user != null) {
            modelAndView.addObject("user", user);
        } else {
            // Nếu không có trong session, kiểm tra Spring Security
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
                UserDetails userDetails = (UserDetails) auth.getPrincipal();
                UserEntity userEntity = userService.getUserByUsername(userDetails.getUsername());
                session.setAttribute("user", userEntity);
                modelAndView.addObject("user", userEntity);
            }
        }

        return modelAndView;
    }

}