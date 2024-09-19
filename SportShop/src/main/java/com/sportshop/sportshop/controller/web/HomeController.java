package com.sportshop.sportshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView homePage() {
        // Tạo đối tượng ModelAndView
        ModelAndView mav = new ModelAndView("home");

        // Thêm dữ liệu vào Model
        mav.addObject("welcomeMessage", "Welcome to the Home Page!");
        mav.addObject("name", "John Doe");

        // Trả về ModelAndView với tên template "home"
        return mav;
    }
}
