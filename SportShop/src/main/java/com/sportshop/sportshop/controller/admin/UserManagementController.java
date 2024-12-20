package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/user")
public class UserManagementController {
    @Autowired
    private UserService userService;

    // View all users
    @GetMapping
    public ModelAndView userManagement() {
        ModelAndView mav = new ModelAndView("admin/user/management");

        mav.addObject("users", userService.getUsers());

        return mav;
    }

    // View User by ID
    @GetMapping("/{userId}")
    public ModelAndView getUserById(@PathVariable Long userId) {

        ModelAndView mav = new ModelAndView("admin/user/view");

        mav.addObject("user", userService.getUserById(userId))
                .addObject("updateUser", new UpdateUserRequest());

        return mav;
    }

    // Create User
    @GetMapping("/create")
    public ModelAndView createUser() {

        return new ModelAndView("admin/user/create")
                    .addObject("newUser", new CreateUserRequest())
                    .addObject("genders", GenderEnum.values());
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("newUser") @Valid CreateUserRequest newUser,
                             @RequestParam("file") MultipartFile file,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
        }
        try {
            userService.createUser(newUser, file);
            model.addAttribute("notification", "Success");
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
        }

        return new ModelAndView("admin/user/management")
                    .addObject("users", userService.getUsers());
    }

    // Update User
    @GetMapping("/update/{userId}")
    public ModelAndView updateUser(@PathVariable Long userId) {
        return new ModelAndView("admin/user/update")
                    .addObject("user", userService.getUserById(userId))
                    .addObject("updateUser", new UpdateUserRequest())
                    .addObject("genders", GenderEnum.values());
    }

    @PostMapping("/update/{userId}")
    public ModelAndView updateUser(@ModelAttribute @Valid UpdateUserRequest updateUser,
                            @RequestParam("file") MultipartFile file,
                            @PathVariable Long userId, BindingResult result,
                            Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
        }
        try {
            userService.updateUser(updateUser, userId, file);
            model.addAttribute("notification", "Success");
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
        }
        return new ModelAndView("admin/user/management")
                    .addObject("users", userService.getUsers());
  
    }

    // Delete User
    @DeleteMapping("/delete/{userId}")
    public ModelAndView deleteUser(@PathVariable Long userId, Model model) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("admin/user/management")
                    .addObject("users", userService.getUsers());
  
    }

}

