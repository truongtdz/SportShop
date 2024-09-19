package com.sportshop.sportshop.controller.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagementController {
    @GetMapping("/user-list")
    public String userManagement() {
        return "admin/user_management";
    }
}
