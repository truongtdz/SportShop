package com.sportshop.sportshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class DefaultController {
    
    @GetMapping("/default")
    public String getMethodName(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin";
        } else 
            return "redirect:/home";
    }
    
}
