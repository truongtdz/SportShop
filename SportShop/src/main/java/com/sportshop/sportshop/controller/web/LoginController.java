package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.dto.request.LoginRequest;
import com.sportshop.sportshop.dto.response.LoginResponse;
import com.sportshop.sportshop.service.LoginService;
import com.sportshop.sportshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("web/login")
                   .addObject("login", new LoginRequest());
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute("auth") LoginRequest request, Model model) {
        try{
            LoginResponse login = loginService.checkLogin(request);
            if(login.isLogin()){
                if(login.getRole().toString().equals("ADMIN")){
                    return "admin/admin";
                }
                else return "web/home";
            } else {
                model.addAttribute("notification", "Fail");
                model.addAttribute("message", "check password");
                return "web/notification";
            }
        } catch (Exception e){
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "web/notification";
        }

    }

}
