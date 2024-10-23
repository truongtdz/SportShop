package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.BrandRequest;
import com.sportshop.sportshop.dto.request.CategoryRequest;
import com.sportshop.sportshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/category")
public class CategoryManagementController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ModelAndView getAllBrand(){
        return new ModelAndView("admin/product/category")
                .addObject("categories", categoryService.getAllCategory())
                .addObject("newCategory", new CategoryRequest());
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute("newCategory") CategoryRequest request, Model model){
        try {
            categoryService.createCategory(request);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public String deleteBrand(@PathVariable String categoryId, Model model){
        try{
            categoryService.deleteCategory(categoryId);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }
}
