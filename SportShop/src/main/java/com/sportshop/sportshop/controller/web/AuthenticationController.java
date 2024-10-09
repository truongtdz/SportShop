package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.LoginRequest;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("web/login")
                   .addObject("login", new LoginRequest());
    }


    @GetMapping("/sign-in")
    public ModelAndView signIn() {
        return new ModelAndView("web/signin")
                .addObject("newUser", new CreateUserRequest());
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute("newUser") CreateUserRequest newUser, BindingResult result, Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
            return "/web/signin";
        }
        try {
            userService.createUser(newUser);
            model.addAttribute("message", "Create Account Success");
            return "/web/login";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/web/signin";
        }
    }

}
