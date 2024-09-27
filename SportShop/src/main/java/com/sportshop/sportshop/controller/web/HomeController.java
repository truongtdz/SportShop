package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public ModelAndView homePage() {
        return new ModelAndView("/web/home")
                .addObject("products", productService.getAllProducts()) ;
    }
}
