package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView admin() {

        ModelAndView mav = new ModelAndView("admin/home");
        mav.addObject("userQuantity", userService.countUsers());

        return mav;
    }
}
