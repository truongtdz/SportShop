package com.sportshop.sportshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.sportshop.sportshop.dto.request.AddressRequest;
import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.service.CartService;
import com.sportshop.sportshop.service.OrderService;
import com.sportshop.sportshop.service.UserService;
import com.sportshop.sportshop.utils.GetUserAuthentication;

@Controller
@RequestMapping("/user")
public class AccountController {   
    @Autowired
    private GetUserAuthentication getUserAuthentication;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/info")
    public ModelAndView viewUser(){
        ModelAndView mav =  new ModelAndView("/user/information")
                .addObject("genders", GenderEnum.values())
                .addObject("updateUser", new CreateUserRequest())
                .addObject("newAddress", new AddressRequest());
        
        UserEntity user = getUserAuthentication.getUser();
        if(user != null){
            mav.addObject("user", user);
        }

        return mav;
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("updateUser") UpdateUserRequest request,
                             @RequestParam("file") MultipartFile file){
        
        UserEntity user = getUserAuthentication.getUser();

        userService.updateUser(request, user.getId(), file);

        return "redirect:/user/info";
    }

    @PostMapping("/add-address")
    public String updateUser(@ModelAttribute("newAddress") AddressRequest request){
        
        UserEntity user = getUserAuthentication.getUser();

        userService.addAddress(user.getId(), request);

        return "redirect:/user/info";
    }

    @GetMapping("/delete-address/{addressId}")
    public String deleteAddress(@PathVariable Long addressId){
        userService.deleteAddress(addressId);

        return "redirect:/user/info";
    }

    @GetMapping("/checkout/{addressId}")
    public ModelAndView checkout(@PathVariable Long addressId, Model model){
        UserEntity user = getUserAuthentication.getUser();

        String message = userService.checkout(user.getId(), addressId);
        model.addAttribute("message", message);

        return new ModelAndView("/user/cart")
                .addObject("carts", cartService.getCart(user.getId()))
                .addObject("user", user);
    }

    @GetMapping("/history")
    public ModelAndView historyBuyProduct(){
        UserEntity user = getUserAuthentication.getUser();
        
        return new ModelAndView("/user/history")
                .addObject("orders", orderService.historyBuy(user.getId()));
    }
}
