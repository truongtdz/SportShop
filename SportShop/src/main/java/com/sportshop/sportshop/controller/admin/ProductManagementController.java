package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.service.BrandService;
import com.sportshop.sportshop.service.CategoryService;
import com.sportshop.sportshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product")
public class ProductManagementController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    // View all product
    @GetMapping
    public ModelAndView getAllProducts(){

        ModelAndView mav = new ModelAndView("/admin/product/management");
        mav.addObject("products", productService.getAllProducts());

        return mav;
    }

    // View product by ID
    @GetMapping("/{productId}")
    public ModelAndView getProduct(@PathVariable Long productId){
        ProductResponse product = productService.getProductById(productId);
        return new ModelAndView("/admin/product/view")
                .addObject("product", product)
                .addObject("brand", product.getBrand())
                .addObject("category", product.getCategory());
    }

    // Create product
    @GetMapping("/create")
    public ModelAndView createProduct(){
        return new ModelAndView("/admin/product/create")
                    .addObject("newProduct",  new ProductRequest())
                    .addObject("categories", categoryService.getAllCategory())
                    .addObject("brands", brandService.getAllBrand());
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("newProduct") ProductRequest request, Model model){
        try {
            productService.createProduct(request);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }

    // Update Product
    @GetMapping("/update/{productId}")
    public ModelAndView updateProduct(@PathVariable Long productId){
        return new ModelAndView("/admin/product/update")
                .addObject("product", productService.getProductById(productId))
                .addObject("updateProduct", new ProductRequest())
                .addObject("categories", categoryService.getAllCategory())
                .addObject("brands", brandService.getAllBrand());
    }

    @PostMapping("/update/{productId}")
    public String updateProduct(@PathVariable Long productId, @ModelAttribute("updateProduct") ProductRequest request, Model model){
        try{
            productService.updateProduct(productId, request);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId, Model model){
        try{
            productService.deleteProduct(productId);
            model.addAttribute("notification", "Success");
            return "/admin/admin";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/admin";
        }
    }

}
