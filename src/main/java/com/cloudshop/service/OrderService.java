package com.cloudshop.service;

import com.cloudshop.persistance.model.Order;
import com.cloudshop.persistance.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public void save(Order order) {
        repository.save(order);
    }

    public List<Order> findAllOrders() {
        return repository.findAll();
    }
}
