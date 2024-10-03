package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.LoginRequest;
import com.sportshop.sportshop.dto.response.LoginResponse;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.service.LoginService;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("web/login")
                   .addObject("login", new LoginRequest());
    }

    @PostMapping("/login")
    public ModelAndView checkLogin(@ModelAttribute("auth") LoginRequest request, Model model, HttpSession session) {
        try{
            LoginResponse login = loginService.checkLogin(request);
            if(login.isLogin()){
                if(login.getUser().getRoles().toString().equals("ADMIN")){
                    return new ModelAndView("admin/admin");
                }
                else {
                    session.setAttribute("user", login.getUser());
                    model.addAttribute("user", login.getUser());
                    model.addAttribute("products", productService.getAllProducts());
                    return new ModelAndView("web/home");
                }
            } else {
                model.addAttribute("message", "Password Is Incorrect");
                return new ModelAndView("web/login");
            }
        } catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("web/login");
        }

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

    @GetMapping("/logout1")
    public String logout(HttpSession session){
        session.invalidate();
        return "web/home";
    }

}
