package com.sportshop.sportshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportshop.sportshop.enums.StatusOrderEnum;
import com.sportshop.sportshop.service.OrderDetailService;
import com.sportshop.sportshop.service.OrderService;


@Controller
@RequestMapping("/admin/order")
public class OrderManagementController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping()
    public ModelAndView orderManagement(){
        return new ModelAndView("/admin/order/management")
                .addObject("orders", orderService.getAllOrder())
                .addObject("status_orders", StatusOrderEnum.values());
    }

    @GetMapping("/{orderId}")
    public ModelAndView getOrderById(@PathVariable Long orderId){
        return new ModelAndView("/admin/order/view")
                .addObject("order", orderService.getOrderById(orderId))
                .addObject("items", orderDetailService.getItemByOrderId(orderId));
    }

    @GetMapping("/{orderId}/{status}")
    public String updateStatusOrder(@PathVariable("orderId") Long orderId,
                                    @PathVariable("status") StatusOrderEnum status) {
        orderService.updateStatusOrder(orderId, status);
        return "redirect:/admin/order";
    }
    
}
