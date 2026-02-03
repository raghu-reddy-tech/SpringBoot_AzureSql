package com.cloudshop.service;

import com.cloudshop.persistance.DTO.OrderDTO;
import com.cloudshop.persistance.model.Order;
import com.cloudshop.persistance.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderDTO saveAndGetOrderDto(Order order) {
        repository.save(order);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus("CREATED");
        orderDTO.setCreationTime(order.getCreatedAt().toString());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setPrice(order.getAmount());
        return orderDTO;
    }

    public List<Order> findAllOrders() {
        return repository.findAll();
    }
}
