package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.response.BillResponse;
import com.sportshop.sportshop.dto.response.OrderResponse;
import com.sportshop.sportshop.service.OrderService;
import com.sportshop.sportshop.service.ProductService;
import com.sportshop.sportshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView getAllOrders() {
        return new ModelAndView("admin/order/management")
                .addObject("orders", orderService.getALlOrders());
    }

    @GetMapping("{orderId}")
    public ModelAndView getOrderById(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        List<BillResponse> billResponses = orderService.getBillById(orderId);
        return new ModelAndView("admin/order/view")
                .addObject("order", orderResponse)
                .addObject("user", userService.getUserById(orderResponse.getUserId()))
                .addObject("bills", billResponses);
    }
}
