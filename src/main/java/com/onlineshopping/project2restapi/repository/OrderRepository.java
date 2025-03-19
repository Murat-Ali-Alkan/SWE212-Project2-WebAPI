package com.onlineshopping.project2restapi.repository;

import com.onlineshopping.project2restapi.dto.OrderDTO;
import com.onlineshopping.project2restapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * from orders o where o.status = :status" , nativeQuery = true)
    List<Order> searchOrdersByStatus(@Param("status") String status);
}
