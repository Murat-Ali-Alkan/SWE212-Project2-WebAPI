package com.onlineshopping.project2restapi.repository;

import com.onlineshopping.project2restapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
