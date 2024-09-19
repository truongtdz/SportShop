package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.enums.CityEnum;
import com.sportshop.sportshop.enums.DistrictEnum;
import com.sportshop.sportshop.enums.GenderEnum;
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

    @GetMapping
    public ModelAndView userManagement() {
        ModelAndView mav = new ModelAndView("admin/user/management");

        mav.addObject("users", userService.getUsers());

        return mav;
    }

    @GetMapping("/{user_id}")
    public ModelAndView getUserById(@PathVariable Long user_id) {

        ModelAndView mav = new ModelAndView("admin/user/view");

        mav.addObject("user", userService.getUserById(user_id));

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createUser() {
        ModelAndView mav = new ModelAndView("admin/user/create");
        mav.addObject("newUser", new CreateUserRequest());
        mav.addObject("genders", GenderEnum.values());
        mav.addObject("cities", CityEnum.values());
        mav.addObject("districts", DistrictEnum.values());
        return mav ;
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") @Valid CreateUserRequest newUser, BindingResult result, Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
            return "/admin/user/notification";
        }
        try {
            userService.createUser(newUser);
            model.addAttribute("notification", "Success");
            return "/admin/user/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/user/notification";
        }
    }

    @GetMapping("/update/{user_id}")
    public ModelAndView updateUser(@PathVariable Long user_id) {
        ModelAndView mav = new ModelAndView("admin/user/update");
        mav.addObject("user", userService.getUserById(user_id));
        mav.addObject("updateUser", new UpdateUserRequest());
        mav.addObject("genders", GenderEnum.values());
        mav.addObject("cities", CityEnum.values());
        mav.addObject("districts", DistrictEnum.values());
        return mav ;
    }

    @PostMapping("/update/{user_id}")
    public String updateUser(@ModelAttribute @Valid UpdateUserRequest updateUser,BindingResult result ,@PathVariable Long user_id , Model model) {
        if(result.hasErrors()){
            String enumKey = result.getFieldError().getDefaultMessage();
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", ErrorCode.valueOf(enumKey).getMessage());
            return "/admin/user/notification";
        }
        try {
            userService.updateUser(updateUser, user_id);
            model.addAttribute("notification", "Success");
            return "/admin/user/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/user/notification";
        }
    }

    @DeleteMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Long user_id, Model model) {
        try {
            userService.deleteUser(String.valueOf(user_id));
            model.addAttribute("notification", "Success");
            return "/admin/user/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/user/notification";
        }
    }

}

