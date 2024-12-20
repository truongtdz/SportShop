package com.sportshop.sportshop.controller.user;

import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.utils.GetUserAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private GetUserAuthentication getUserAuthentication;

    @GetMapping("/{userId}")
    public ModelAndView getCart(@PathVariable Long userId){
        UserEntity user = getUserAuthentication.getUser();

        ModelAndView mav = new ModelAndView("/user/cart")
                .addObject("carts", cartService.getCart(userId))
                .addObject("user", user);

        return mav;
    }

    @PostMapping("/{productId}")
    public ModelAndView addCart(@PathVariable Long productId, Model model){
        UserEntity user = getUserAuthentication.getUser();
        
        ModelAndView mav = new ModelAndView("/user/cart")
                        .addObject("user", user);
        if(cartService.addCart(user.getId(), productId)){
            model.addAttribute("notification", "Success");
            model.addAttribute("message", "Thêm Sản Phẩm Thành Công ");
        } else {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", "Sản Phẩm Này Đã Có Trong Giỏ Hàng Rồi");
        }    
        mav.addObject("carts", cartService.getCart(user.getId()));
        return mav;
    }

    @GetMapping("/{productId}/{quantity}")
    public ModelAndView replaceQuantityProduct(Model model,@PathVariable("productId") Long productId,
                                        @PathVariable("quantity") Long quantity){
                                            
        UserEntity user = getUserAuthentication.getUser();

        ModelAndView mav = new ModelAndView("/user/cart")
                            .addObject("carts", cartService.getCart(user.getId()))
                            .addObject("user", user);
            
        if(cartService.replaceQuantityProduct(user.getId(), productId, quantity)){
            model.addAttribute("notification", "Success");
            model.addAttribute("message", "Thêm thành công");
        } else {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", "Không thể mua thêm vì không đủ hàng");
        }
        return mav;
    }

    @GetMapping("/delete/{productId}")
    public String deleteCart(@PathVariable Long productId){
        UserEntity user = getUserAuthentication.getUser();
        cartService.removeProductToCart(user.getId(), productId);
        return "redirect:/user/cart/" + user.getId();
    }
}
