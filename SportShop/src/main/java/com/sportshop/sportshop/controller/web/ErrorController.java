package com.sportshop.sportshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorController {
    @GetMapping("/no-access")
    public ModelAndView getMethodName() {
        return new ModelAndView("web/no-access");
    }
    
}
