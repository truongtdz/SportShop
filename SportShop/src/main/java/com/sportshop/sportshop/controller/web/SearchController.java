package com.sportshop.sportshop.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportshop.sportshop.dto.request.SearchRequest;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.SortEnum;
import com.sportshop.sportshop.service.BrandService;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.service.CategoryService;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.utils.GetUserAuthentication;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GetUserAuthentication getUserAuthentication;

    @PostMapping("/home/search-name")
    public ModelAndView searchProductByName(@RequestParam(required = false) String name) {
        ModelAndView mav = new ModelAndView("/web/search")
                .addObject("newSearch", new SearchRequest())
                .addObject("products", productService.searchProductByNameOrDescription(name))
                .addObject("categories", categoryService.getAllCategory())
                .addObject("brands", brandService.getAllBrand())
                .addObject("sorts", SortEnum.values());
        
        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("count", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }

    @GetMapping("/home/search")
    public ModelAndView searchProduct() {
        ModelAndView mav = new ModelAndView("/web/search")
                .addObject("newSearch", new SearchRequest())
                .addObject("products", productService.getAllProducts())
                .addObject("categories", categoryService.getAllCategory())
                .addObject("brands", brandService.getAllBrand())
                .addObject("sorts", SortEnum.values());
        
        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("count", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }

    @PostMapping("/home/search")
    public ModelAndView search(@ModelAttribute("newSearch") SearchRequest request){
        ModelAndView mav = new ModelAndView("/web/search")
                .addObject("newSearch", new SearchRequest())
                .addObject("products", productService.searchProduct(request))
                .addObject("categories", categoryService.getAllCategory())
                .addObject("brands", brandService.getAllBrand())
                .addObject("sorts", SortEnum.values());

        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("count", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }

    @GetMapping("/home/search/{brandId}")
    public ModelAndView searchProduct(@PathVariable("brandId") String brandId) {
        SearchRequest newSearch = new SearchRequest();
        List<String> brands = new ArrayList<>();
        brands.add(brandId);
        newSearch.setBrands(brands);
        newSearch.setTypeSort(SortEnum.NOT_SORT);

        ModelAndView mav = new ModelAndView("/web/search")
                .addObject("newSearch", new SearchRequest())
                .addObject("products", productService.searchProduct(newSearch))
                .addObject("categories", categoryService.getAllCategory())
                .addObject("brands", brandService.getAllBrand())
                .addObject("sorts", SortEnum.values());
        
        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("count", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }
}
