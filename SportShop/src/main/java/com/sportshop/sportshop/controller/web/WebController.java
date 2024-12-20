package com.sportshop.sportshop.controller.web;

import com.sportshop.sportshop.entity.CommentEntity;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.BrandService;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.service.CommentService;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.utils.GetUserAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GetUserAuthentication getUserAuthentication;

    @Autowired
    private CommentService commentService;

    @GetMapping()
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/home");

        mav.addObject("brands", brandService.getAllBrand());
        mav.addObject("products", productService.getAllProducts());
        mav.addObject("productSales", productService.getProductSale());
        mav.addObject("productDates", productService.getProductNewest());
        mav.addObject("quantityProduct", productService.getProductNewest().size());
        
        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("quantity", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }

    @GetMapping("/contact")
    public ModelAndView getContact() {
        return new ModelAndView("web/contact");
    }

    @GetMapping("/introduce")
    public ModelAndView getIntroduce() {
        return new ModelAndView("web/introduce");
    }
    
    @GetMapping("/product/{productId}")
    public ModelAndView getProductDetail(@PathVariable Long productId) {
        ModelAndView mav = new ModelAndView("web/product_detail")
                .addObject("product", productService.getProductById(productId))
                .addObject("newComment", new CommentEntity())
                .addObject("comments", commentService.findByProductId(productId));

        UserEntity user = getUserAuthentication.getUser();
        if (user != null) {
            mav.addObject("user", user);
            mav.addObject("count", cartService.getCountByUserId(user.getId()));
        }

        return mav;
    }
    
}