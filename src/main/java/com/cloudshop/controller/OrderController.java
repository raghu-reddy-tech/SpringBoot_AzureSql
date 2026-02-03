package com.cloudshop.controller;

import com.cloudshop.persistance.DTO.OrderDTO;
import com.cloudshop.persistance.model.Order;
import com.cloudshop.service.OrderFunctionClient;
import com.cloudshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderFunctionClient orderFunctionClient;

    @Autowired
    public OrderController(OrderService orderService, OrderFunctionClient orderFunctionClient) {
        this.orderService = orderService;
        this.orderFunctionClient = orderFunctionClient;
    }

    // Show Order Form
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    // Create Order
    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order) {
        order.setStatus("CREATED");
        OrderDTO orderDTO = orderService.saveAndGetOrderDto(order);
        orderFunctionClient.triggerFunctionAppOrderProcessing(orderDTO);
        return "redirect:/orders/list";
    }

    // List Orders
    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "order-list";
    }

}

