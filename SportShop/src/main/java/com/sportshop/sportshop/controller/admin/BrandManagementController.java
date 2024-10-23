package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.BrandRequest;
import com.sportshop.sportshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/brand")
public class BrandManagementController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public ModelAndView getAllBrand(){
        return new ModelAndView("admin/product/brand")
                .addObject("brands", brandService.getAllBrand())
                .addObject("newBrand", new BrandRequest());
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute("newBrand") BrandRequest request, Model model){
        try {
            brandService.createBrand(request);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }

    @DeleteMapping("/delete/{brandId}")
    public String deleteBrand(@PathVariable String brandId, Model model){
        try{
            brandService.deleteBrand(brandId);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }
}
