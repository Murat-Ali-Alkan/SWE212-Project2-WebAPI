package com.onlineshopping.project2restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineshopping.project2restapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
