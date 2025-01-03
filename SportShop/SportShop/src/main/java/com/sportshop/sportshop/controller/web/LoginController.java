package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.LoginRequest;
import com.sportshop.sportshop.dto.response.LoginResponse;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.service.LoginService;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("web/login")
                   .addObject("login", new LoginRequest());
    }

    @PostMapping("/login")
    public ModelAndView checkLogin(@ModelAttribute("auth") LoginRequest request, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy thông tin người dùng từ authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                return new ModelAndView("admin/admin");
            } else {
                model.addAttribute("user", userDetails);
                model.addAttribute("products", productService.getAllProducts());
                return new ModelAndView("web/home");
            }
        } catch (AuthenticationException e) {
            model.addAttribute("message", "Invalid username or password");
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

    @GetMapping("/log-out")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

}
