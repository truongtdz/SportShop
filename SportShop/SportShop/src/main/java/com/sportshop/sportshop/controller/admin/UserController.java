package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.enums.CityEnum;
import com.sportshop.sportshop.enums.DistrictEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.RoleEnum;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
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

        mav.addObject("user", userService.getUserById(userId));

        return mav;
    }

    // Create User
    @GetMapping("/create")
    public ModelAndView createUser() {

        return new ModelAndView("admin/user/create")
                    .addObject("newUser", new CreateUserRequest())
                    .addObject("genders", GenderEnum.values())
                    .addObject("cities", CityEnum.values())
                    .addObject("districts", DistrictEnum.values())
                    .addObject("roles", RoleEnum.values());
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") @Valid CreateUserRequest newUser, BindingResult result, Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
            return "/web/notification";
        }
        try {
            userService.createUser(newUser);
            model.addAttribute("notification", "Success");
            return "/web/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/web/notification";
        }
    }

    // Update User
    @GetMapping("/update/{userId}")
    public ModelAndView updateUser(@PathVariable Long userId) {
        return new ModelAndView("admin/user/update")
                    .addObject("user", userService.getUserById(userId))
                    .addObject("updateUser", new UpdateUserRequest())
                    .addObject("genders", GenderEnum.values())
                    .addObject("cities", CityEnum.values())
                    .addObject("districts", DistrictEnum.values())
                    .addObject("roles", RoleEnum.values());
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@ModelAttribute @Valid UpdateUserRequest updateUser,BindingResult result ,@PathVariable Long userId , Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
            return "/web/notification";
        }
        try {
            userService.updateUser(updateUser, userId);
            model.addAttribute("notification", "Success");
            return "/web/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/web/notification";
        }
    }

    // Delete User
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        try {
            userService.deleteUser(String.valueOf(userId));
            model.addAttribute("notification", "Success");
            return "/web/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/web/notification";
        }
    }

}

