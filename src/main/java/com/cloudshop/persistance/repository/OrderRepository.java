package com.cloudshop.persistance.repository;

import com.cloudshop.persistance.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
